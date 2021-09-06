package me.linkaipeng.account

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM UserTable LIMIT 1")
    fun getData(): LiveData<UserModel>
}