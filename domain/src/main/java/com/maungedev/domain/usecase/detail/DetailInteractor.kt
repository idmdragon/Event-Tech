package com.maungedev.domain.usecase.detail

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.Event
import com.maungedev.domain.repository.EventRepository

class DetailInteractor(private val eventRepository: EventRepository): DetailUseCase {

    override fun getEventById(id: String): Flow<Resource<Event>> =
        eventRepository.getEventById(id)

    override fun addSchedule(id: String): Flow<Resource<Unit>> =
        eventRepository.addSchedule(id)

    override fun unsaveEvent(id: String): Flow<Resource<Unit>> =
        eventRepository.unsaveEvent(id)

    override fun saveEvent(id: String): Flow<Resource<Unit>> =
        eventRepository.saveEvent(id)
}