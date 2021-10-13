package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.repository.EventRepository

class DetailInteractor(private val eventITRepository: EventRepository): DetailUseCase {

    override fun getEventById(id: String): Flow<Resource<Event>> =
        eventITRepository.getEventById(id)
}