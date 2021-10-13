package com.maungedev.domain.repository

import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EventRepository {
    fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>>
    fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>>
    fun getAllEventConference(): Flow<Resource<List<Event>>>
    fun getAllEventCompetition(): Flow<Resource<List<Event>>>
}