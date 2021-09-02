package me.linkaipeng.roomdemo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM User LIMIT 1")
    fun getCurrentUser(): UserModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudyUser(studyUserModel: UserModel)

    @Query("DELETE FROM User")
    fun deleteAllStudyUser()
}