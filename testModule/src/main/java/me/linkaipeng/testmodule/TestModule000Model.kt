package me.linkaipeng.testmodule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TestModule000")
data class TestModule000Model(
    @PrimaryKey
    val test00: String = ""
)