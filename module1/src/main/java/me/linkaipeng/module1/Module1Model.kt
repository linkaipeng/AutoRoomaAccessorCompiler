package me.linkaipeng.module1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Module1Table")
data class Module1Model(
    @PrimaryKey
    val test00: String = ""
)