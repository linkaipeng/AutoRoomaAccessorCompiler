## 背景

## 使用方式

#### 对应子 module 配置

```
...
apply plugin: 'kotlin-kapt'

android {
    ...

    defaultConfig {
        ...

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [ROOM_ACCESSOR_NAME_PREFIX: "TestModule"]
            }
        }
    }
}

dependencies {
    ...
    kapt project(':autoRoomAccessorCompiler')
}
```

## 代码生成

上面配置后，此库将会扫描 module 中用到 Room 数据库中 @Dao 的类，生成对应代码到 位于 module 的 /build/generated/source/kapt 下面

**类名为 ROOM_ACCESSOR_NAME_PREFIX + RoomAccessor**

## 主 App 使用

```
Module1RoomAccessor.onGetDaoCallback = object : Module1RoomAccessor.OnGetDaoCallback {
     override fun onGetModule1Dao(): Module1Dao {
         return DBHelper.db.module1Dao()
     }
}
```


## FAQ

#### 抛异常 "onGetDaoCallback must not be null!!" 

XXXRoomAccessor 的 onGetDaoCallback 必须实现，没实现就会抛此异常；
