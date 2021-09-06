package me.linkaipeng.testmodule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsDetailTable")
data class NewsDetailModel(
    @PrimaryKey
    val content: String = ""
)