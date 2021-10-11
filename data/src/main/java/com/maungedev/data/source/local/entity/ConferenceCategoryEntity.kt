package com.maungedev.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conference_category")
data class ConferenceCategoryEntity(
    @PrimaryKey
    val id: String,
    val categoryName: String,
    val categoryIcon: String,
)