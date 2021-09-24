package com.maungedev.domain.model

data class EventIT(
    val uid: String,
    val eventName: String,
    val eventType: String,
    val eventCategory: String,
    val price: String,
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
) {
}