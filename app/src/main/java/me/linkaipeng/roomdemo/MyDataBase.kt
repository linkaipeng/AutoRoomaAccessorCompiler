package me.linkaipeng.roomdemo

import androidx.room.Database
import androidx.room.RoomDatabase
import me.linkaipeng.module1.Module1Dao
import me.linkaipeng.module1.Module1Model
import me.linkaipeng.testmodule.TestModule000Dao
import me.linkaipeng.testmodule.TestModule000Model
import me.linkaipeng.testmodule.TestModule001Dao
import me.linkaipeng.testmodule.TestModule001Model

@Database(
    entities = [
        UserModel::class,
        TestModule000Model::class,
        TestModule001Model::class,
        Module1Model::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TestDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun test0Dao(): TestModule000Dao
    abstract fun test1Dao(): TestModule001Dao
    abstract fun module1Dao(): Module1Dao
}