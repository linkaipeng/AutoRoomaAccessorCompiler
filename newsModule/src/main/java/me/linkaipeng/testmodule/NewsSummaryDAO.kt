package me.linkaipeng.testmodule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsSummaryDAO {
    @Query("SELECT * FROM NewsSummaryTable LIMIT 1")
    fun getData(): NewsSummaryModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(testModel: NewsSummaryModel)
}