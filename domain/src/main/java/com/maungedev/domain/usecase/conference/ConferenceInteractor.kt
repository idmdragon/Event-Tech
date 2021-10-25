package com.maungedev.domain.usecase.conference

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.repository.EventRepository

class ConferenceInteractor(private val eventITRepository: EventRepository): ConferenceUseCase {

    override fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>> =
        eventITRepository.getConferenceCategory()

    override fun getAllEventConference(): Flow<Resource<List<Event>>> =
        eventITRepository.getAllEventConference()

    override fun getEventByCategories(categories: String): Flow<Resource<List<Event>>> =
        eventITRepository.getEventConferenceByCategories(categories)
}