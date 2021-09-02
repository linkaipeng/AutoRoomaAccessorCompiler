package me.linkaipeng.module1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface Module1Dao {
    @Query("SELECT * FROM Module1Table LIMIT 1")
    fun getData(): LiveData<Module1Model>
}