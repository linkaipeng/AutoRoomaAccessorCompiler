package me.linkaipeng.roomdemo

import android.app.Application
import me.linkaipeng.auto.room.AppModuleRoomAccessor
import me.linkaipeng.auto.room.Module1RoomAccessor
import me.linkaipeng.auto.room.TestModuleRoomAccessor
import me.linkaipeng.module1.Module1Dao
import me.linkaipeng.testmodule.TestModule000Dao
import me.linkaipeng.testmodule.TestModule001Dao

class App: Application() {

    companion object {
        lateinit var application: App
    }

    override fun onCreate() {
        super.onCreate()
        application = this


        AppModuleRoomAccessor.onGetDaoCallback = object : AppModuleRoomAccessor.OnGetDaoCallback {
            override fun onGetUserDao(): UserDao {
                return DBHelper.db.userDao()
            }
        }

        Module1RoomAccessor.onGetDaoCallback = object : Module1RoomAccessor.OnGetDaoCallback {
            override fun onGetModule1Dao(): Module1Dao {
                return DBHelper.db.module1Dao()
            }
        }

        TestModuleRoomAccessor.onGetDaoCallback = object : TestModuleRoomAccessor.OnGetDaoCallback {
            override fun onGetTestModule000Dao(): TestModule000Dao {
               return DBHelper.db.test0Dao()
            }

            override fun onGetTestModule001Dao(): TestModule001Dao {
                return DBHelper.db.test1Dao()
            }
        }
    }
}