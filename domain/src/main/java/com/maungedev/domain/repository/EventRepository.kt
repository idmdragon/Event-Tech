package com.maungedev.domain.repository

import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>>
    fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>>
    fun getAllEventConference(): Flow<Resource<List<Event>>>
    fun getAllEventCompetition(): Flow<Resource<List<Event>>>
    fun getEventConferenceByCategories(categories: String): Flow<Resource<List<Event>>>
    fun getEventById(id: String): Flow<Resource<Event>>
    fun getAllSchedule(ids: List<String>):Flow<Resource<List<Event>>>
    fun deleteSchedule(id: String):Flow<Resource<Unit>>
    fun addSchedule(id: String):Flow<Resource<Unit>>
    fun unsaveEvent(id: String):Flow<Resource<Unit>>
    fun saveEvent(id: String):Flow<Resource<Unit>>
    fun getCurrentUser(): Flow<Resource<User>>
    fun getCurrentUserId(): String
    fun getUser(id: String): Flow<Resource<User>>

}