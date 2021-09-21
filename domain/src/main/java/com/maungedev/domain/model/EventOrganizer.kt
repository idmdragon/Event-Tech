package com.maungedev.domain.model

data class EventOrganizer(
    val uid: String,
    val username: String,
    val email: String,
    val avatar: String,
    val myEvent: List<String>
) {
}