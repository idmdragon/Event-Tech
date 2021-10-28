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
    fun getAllPopularEvent(): Flow<Resource<List<Event>>>
    fun getEventsConferenceByCategories(categories: String): Flow<Resource<List<Event>>>
    fun getEventsCompetitionByCategories(categories: String): Flow<Resource<List<Event>>>
    fun getEventById(id: String): Flow<Resource<Event>>
    fun getAllSchedule(ids: List<String>):Flow<Resource<List<Event>>>
    fun deleteSchedule(id: String):Flow<Resource<Unit>>
    fun addSchedule(id: String):Flow<Resource<Unit>>
    fun addFavoriteEvent(id: String):Flow<Resource<Unit>>
    fun deleteFavoriteEvent(id: String):Flow<Resource<Unit>>
    fun getAllFavorite(ids: List<String>):Flow<Resource<List<Event>>>
    fun getCurrentUser(): Flow<Resource<User>>
    fun getCurrentUserId(): String
    fun getUser(id: String): Flow<Resource<User>>
    fun increaseNumbersOfRegistrationClick(id:String):Flow<Unit>
    fun increaseNumbersOfView(id:String):Flow<Unit>
    fun searchConference(title:String):Flow<Resource<List<Event>>>
    fun searchCompetition(title:String):Flow<Resource<List<Event>>>
    fun refreshAllEvent(): Flow<Resource<Unit>>

}