package com.maungedev.domain.usecase.detail

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.repository.EventRepository

class DetailInteractor(private val eventRepository: EventRepository): DetailUseCase {

    override fun getEventById(id: String): Flow<Resource<Event>> =
        eventRepository.getEventById(id)

    override fun addSchedule(id: String): Flow<Resource<Unit>> =
        eventRepository.addSchedule(id)

    override fun deleteFavoriteEvent(id: String): Flow<Resource<Unit>> =
        eventRepository.deleteFavoriteEvent(id)

    override fun addFavoriteEvent(id: String): Flow<Resource<Unit>> =
        eventRepository.addFavoriteEvent(id)

    override fun getCurrentUser(): Flow<Resource<User>> =
        eventRepository.getCurrentUser()

    override fun deleteSchedule(id: String): Flow<Resource<Unit>> =
        eventRepository.deleteSchedule(id)

    override fun increaseNumbersOfRegistrationClick(id: String): Flow<Unit> =
        eventRepository.increaseNumbersOfRegistrationClick(id)

    override fun increaseNumbersOfView(id: String): Flow<Unit>  =
        eventRepository.increaseNumbersOfView(id)
}