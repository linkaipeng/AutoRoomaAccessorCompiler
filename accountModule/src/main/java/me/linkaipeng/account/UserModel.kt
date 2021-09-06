package me.linkaipeng.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class UserModel(
    @PrimaryKey
    val userName: String = ""
)