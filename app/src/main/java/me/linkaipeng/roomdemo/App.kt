package me.linkaipeng.roomdemo

import android.app.Application
import me.linkaipeng.account.UserDao
import me.linkaipeng.auto.room.*
import me.linkaipeng.testmodule.NewsSummaryDAO
import me.linkaipeng.testmodule.NewsDetailDAO

class App: Application() {

    companion object {
        lateinit var application: App
    }

    override fun onCreate() {
        super.onCreate()
        application = this

        AccountModuleRoomAccessor.onGetDaoCallback = object : AccountModuleRoomAccessor.OnGetDaoCallback {
            override fun onGetUserDao(): UserDao {
                return DBHelper.db.userDao()
            }
        }

        NewsModuleRoomAccessor.onGetDaoCallback = object : NewsModuleRoomAccessor.OnGetDaoCallback {
            override fun onGetNewsDetailDAO(): NewsDetailDAO {
                return DBHelper.db.newsDetailDao()
            }
            override fun onGetNewsSummaryDAO(): NewsSummaryDAO {
                return DBHelper.db.newsSummaryDao()
            }
        }
    }
}