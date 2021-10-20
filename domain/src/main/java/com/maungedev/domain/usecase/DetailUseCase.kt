package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event

interface DetailUseCase {
    fun getEventById(id:String): Flow<Resource<Event>>
    fun addSchedule(id: String):Flow<Resource<Unit>>
    fun unsaveEvent(id: String):Flow<Resource<Unit>>
    fun saveEvent(id: String):Flow<Resource<Unit>>
}