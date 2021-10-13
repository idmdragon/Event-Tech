package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event

interface ConferenceUseCase {
    fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>>
    fun getAllEventConference(): Flow<Resource<List<Event>>>
    fun getEventByCategories(categories: String): Flow<Resource<List<Event>>>
}