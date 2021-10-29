package com.maungedev.domain.usecase.conference

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event

interface ConferenceUseCase {
    fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>>
    fun getAllEventConference(): Flow<Resource<List<Event>>>
    fun getEventConferenceByCategories(categories: String): Flow<Resource<List<Event>>>
    fun getAllPopularEvent(): Flow<Resource<List<Event>>>
    fun refreshAllEvent(): Flow<Resource<Unit>>
}