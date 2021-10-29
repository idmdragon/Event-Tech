package com.maungedev.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey
    val uid: String ="",
    val eventName: String ="",
    val eventType: String = "",
    val eventCategory: String ="",
    val price: Long = 0,
    val date: Long = 0,
    val time: String = "",
    val location: String ="",
    val linkRegistration: String ="",
    val description: String = "",
    val prerequisite: String = "",
    val eventCover: String ="",
    val numbersOfView: Int = 0,
    val numbersOfRegistrationClick: Int = 0,
    val organizer: String = ""
)
