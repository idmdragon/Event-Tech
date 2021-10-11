package com.maungedev.domain.model

data class User(
    val uid: String,
    val username: String,
    val email: String,
    val schedule: List<String> = listOf(),
    val favorite: List<String> = listOf(),
)