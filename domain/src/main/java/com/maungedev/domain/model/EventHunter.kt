package com.maungedev.domain.model

data class EventHunter(
    val uid: String,
    val username: String,
    val email: String,
    val schedule: List<*>,
    val favorite: List<*>
) {
}