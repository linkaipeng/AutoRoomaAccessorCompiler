package me.linkaipeng.testmodule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface NewsDetailDAO {
    @Query("SELECT * FROM NewsDetailTable LIMIT 1")
    fun getData(): LiveData<NewsDetailModel>
}