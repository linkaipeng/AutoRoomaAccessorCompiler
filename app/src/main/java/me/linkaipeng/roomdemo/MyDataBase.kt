package me.linkaipeng.roomdemo

import androidx.room.Database
import androidx.room.RoomDatabase
import me.linkaipeng.account.UserDao
import me.linkaipeng.account.UserModel
import me.linkaipeng.testmodule.NewsSummaryDAO
import me.linkaipeng.testmodule.NewsDetailDAO
import me.linkaipeng.testmodule.NewsDetailModel
import me.linkaipeng.testmodule.NewsSummaryModel

@Database(
    entities = [
        UserModel::class,
        NewsDetailModel::class,
        NewsSummaryModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TestDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun newsSummaryDao(): NewsSummaryDAO
    abstract fun newsDetailDao(): NewsDetailDAO
}