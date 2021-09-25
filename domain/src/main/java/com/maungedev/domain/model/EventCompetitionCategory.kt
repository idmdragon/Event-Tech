package com.maungedev.domain.model

data class EventCompetitionCategory(
    val id: Int,
    val categoryName: String,
    var isSelected: Boolean = false
)