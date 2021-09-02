package me.linkaipeng.roomdemo

import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

object DBHelper {
    private const val TAG = "DBHelper"
    private const val DB_PREFIX = "test_info"

    private val dataBase =
        Room.databaseBuilder(App.application, TestDataBase::class.java, DB_PREFIX)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Log.d(TAG, "Room database onCreate in thread " + Thread.currentThread().name)
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    Log.d(TAG, "Room database onOpen in thread " + Thread.currentThread().name)
                }
            })
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    val db: TestDataBase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        dataBase
    }
}