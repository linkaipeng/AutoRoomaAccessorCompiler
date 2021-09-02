package me.linkaipeng.testmodule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestModule000Dao {
    @Query("SELECT * FROM TestModule000 LIMIT 1")
    fun getData(): TestModule000Model

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(testModel: TestModule000Model)
}