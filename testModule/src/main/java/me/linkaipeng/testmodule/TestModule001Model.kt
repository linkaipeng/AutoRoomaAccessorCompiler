package me.linkaipeng.testmodule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TestModule001")
data class TestModule001Model(
    @PrimaryKey
    val test00: String = ""
)