package me.linkaipeng.testmodule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface TestModule001Dao {
    @Query("SELECT * FROM TestModule001 LIMIT 1")
    fun getData(): LiveData<TestModule001Model>
}