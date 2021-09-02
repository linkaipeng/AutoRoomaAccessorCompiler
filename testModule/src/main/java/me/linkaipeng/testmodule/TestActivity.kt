package me.linkaipeng.testmodule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import me.linkaipeng.auto.room.TestModuleRoomAccessor

class TestActivity: AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, TestActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TestModuleRoomAccessor.getTestModule000Dao().insertData(TestModule000Model("哈哈哈哈哈哈"))

        Log.d("RoomDemo", "query data = ${TestModuleRoomAccessor.getTestModule000Dao().getData()}")

    }

}