package com.maungedev.domain.usecase.conference

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.repository.EventRepository

class ConferenceInteractor(private val eventRepository: EventRepository): ConferenceUseCase {

    override fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>> =
        eventRepository.getConferenceCategory()

    override fun getAllEventConference(): Flow<Resource<List<Event>>> =
        eventRepository.getAllEventConference()

    override fun getEventConferenceByCategories(categories: String): Flow<Resource<List<Event>>> =
        eventRepository.getEventsConferenceByCategories(categories)

    override fun getAllPopularEvent(): Flow<Resource<List<Event>>> =
        eventRepository.getAllPopularEvent()
}