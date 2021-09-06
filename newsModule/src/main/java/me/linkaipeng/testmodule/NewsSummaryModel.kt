package me.linkaipeng.testmodule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsSummaryTable")
data class NewsSummaryModel(
    @PrimaryKey
    val title: String = ""
)