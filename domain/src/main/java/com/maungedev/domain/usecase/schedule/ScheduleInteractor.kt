package com.maungedev.domain.usecase.schedule

import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.repository.EventRepository
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


class ScheduleInteractor (private val eventRepository: EventRepository): ScheduleUseCase {
    override fun getAllSchedule(ids: List<String>): Flow<Resource<List<Event>>> =
        eventRepository.getAllSchedule(ids)

    override fun deleteSchedule(id: String): Flow<Resource<Unit>> =
        eventRepository.deleteSchedule(id)

    override fun getCurrentUser(): Flow<Resource<User>> =
        eventRepository.getCurrentUser()
}