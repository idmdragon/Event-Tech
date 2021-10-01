package com.maungedev.data.source.local.entity

import androidx.room.Entity

@Entity(tableName = "User")
data class UserEntity(
    val uid: String,
    val username: String,
    val email: String,
    val schedule: String,
    val favorite: String
) {
}