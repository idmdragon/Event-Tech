package com.maungedev.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competition_category")
data class CompetitionCategoryEntity(
    @PrimaryKey
    val id: String,
    val categoryName: String,
)