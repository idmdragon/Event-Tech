package com.maungedev.data.mapper

import com.maungedev.data.source.local.entity.CompetitionCategoryEntity
import com.maungedev.data.source.local.entity.ConferenceCategoryEntity
import com.maungedev.data.source.local.entity.EventEntity
import com.maungedev.data.source.remote.response.CompetitionCategoryResponse
import com.maungedev.data.source.remote.response.ConferenceCategoryResponse
import com.maungedev.data.source.remote.response.EventResponse
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun EventEntity.toModel(): Event =
    Event(
        uid,
        eventName,
        eventType,
        eventCategory,
        price,
        date,
        time,
        location,
        linkRegistration,
        description,
        prerequisite,
        eventCover,
        numbersOfView,
        numbersOfRegistrationClick,
        favoriteBy,
        organizer
    )

fun EventResponse.toEntity(): EventEntity =
    EventEntity(
        uid,
        eventName,
        eventType,
        eventCategory,
        price,
        date,
        time,
        location,
        linkRegistration,
        description,
        prerequisite,
        eventCover,
        numbersOfView,
        numbersOfRegistrationClick,
        favoriteBy,
        organizer
    )

fun List<EventEntity>.toListModel(): List<Event> =
    this.map {
        it.toModel()
    }

fun List<EventResponse>.toListEntity(): List<EventEntity> =
    this.map {
        it.toEntity()
    }

fun Flow<List<EventEntity>>.toListFlowModel(): Flow<List<Event>> =
    this.map {
        it.toListModel()
    }
