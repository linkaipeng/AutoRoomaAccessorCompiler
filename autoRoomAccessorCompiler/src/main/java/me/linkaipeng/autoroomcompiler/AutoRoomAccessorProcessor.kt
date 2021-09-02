package me.linkaipeng.autoroomcompiler

import androidx.room.Dao
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("androidx.room.Dao")
class AutoRoomAccessorProcessor : AbstractProcessor() {

    companion object {
        private const val PACKAGE_NAME = "me.linkaipeng.auto.room"
    }

    private var messager: Messager?= null
    private var elementUtil: Elements? = null
    private var filer: Filer? = null
    private var generated = false

    private var className = ""

    @Synchronized
    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        filer = processingEnv.filer
        elementUtil = processingEnv.elementUtils
        messager = processingEnv.messager

        if (processingEnvironment.options.containsKey("ROOM_ACCESSOR_NAME_PREFIX")) {
            className = "${processingEnvironment.options["ROOM_ACCESSOR_NAME_PREFIX"]}RoomAccessor"
        } else {
            messager?.printMessage(Diagnostic.Kind.ERROR, "ROOM_ACCESSOR_NAME_PREFIX must be defined.")
            throw IllegalArgumentException()
        }
        if (className.isNullOrEmpty()) {
            messager?.printMessage(Diagnostic.Kind.ERROR, "ROOM_ACCESSOR_NAME_PREFIX must be defined.")
            throw IllegalArgumentException()
        }

        println(" ----------- AutoRoomAccessorProcessor ----------- ")
        println(" ----------- className = $className ----------- ")
    }


    override fun process(
        set: Set<TypeElement?>,
        roundEnvironment: RoundEnvironment
    ): Boolean {
        if (generated || filer == null) {
            return true
        }
        val daoFunList = mutableListOf<FunSpec>()
        val interfaceFuns = mutableListOf<FunSpec>()

        // @Dao
        for (element in roundEnvironment.getElementsAnnotatedWith(Dao::class.java)) {
            val daoClassName = element.simpleName.toString()
            println("find DAO = $daoClassName")

            val daoTypeName = ClassName(elementUtil?.getPackageOf(element).toString(), daoClassName).copy(nullable = false)

            daoFunList.add(
                FunSpec.builder("get$daoClassName")
                    .addModifiers(KModifier.INTERNAL)
                    .returns(daoTypeName)
                    .addCode("if (onGetDaoCallback == null) {\n" +
                            "throw IllegalArgumentException(\"onGetDaoCallback must not be null!!\")\n" +
                            "}\n" +
                            "return onGetDaoCallback!!.onGet$daoClassName()\n")
                    .build()
            )

            interfaceFuns.add(
                FunSpec.builder("onGet$daoClassName")
                    .addModifiers(KModifier.ABSTRACT)
                    .returns(daoTypeName)
                    .build()
            )
        }

        val infoClazzBuilder: TypeSpec.Builder = TypeSpec.objectBuilder(className)
            .addFunctions(daoFunList)

        buildCallback(className, interfaceFuns, infoClazzBuilder)


        val javaFile = FileSpec
            .builder(PACKAGE_NAME, className)
            .addType(infoClazzBuilder.build())
            .build()

        try {
            javaFile.writeTo(filer!!)
            generated = true
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return true
    }

    private fun buildCallback(className: String, interfaceFuns: MutableList<FunSpec>, builder: TypeSpec.Builder) {
        // 内部接口类
        val interfaceInnerClass: TypeSpec = TypeSpec.interfaceBuilder("OnGetDaoCallback")
            .addFunctions(interfaceFuns)
            .build()

        val onGetDaoCallbackType: TypeName = ClassName("${PACKAGE_NAME}.${className}", interfaceInnerClass.name!!)
        val onGetDaoCallbackProperty = PropertySpec.builder("onGetDaoCallback", onGetDaoCallbackType.copy(nullable = true))
            .mutable(true)
            .initializer("null")
            .build()

        builder
            .addType(interfaceInnerClass)
            .addProperty(onGetDaoCallbackProperty)
    }
}