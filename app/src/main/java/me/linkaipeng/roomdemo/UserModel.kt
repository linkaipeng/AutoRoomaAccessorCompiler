package me.linkaipeng.roomdemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserModel(
    @PrimaryKey
    val uid: String = ""
)