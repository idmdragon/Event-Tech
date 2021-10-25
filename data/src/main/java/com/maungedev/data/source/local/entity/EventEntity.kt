package com.maungedev.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey
    val uid: String,
    val eventName: String,
    val eventType: String,
    val eventCategory: String,
    val price: Long,
    val date: String,
    val time: String,
    val location: String,
    val linkRegistration: String,
    val description: String,
    val prerequisite: String,
    val eventCover: String,
    val numbersOfView: Int,
    val numbersOfRegistrationClick: Int,
    var favoriteBy: List<String>?,
    val organizer: String
)
