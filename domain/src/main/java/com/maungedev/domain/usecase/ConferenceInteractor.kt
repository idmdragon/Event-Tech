package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.repository.EventRepository

class ConferenceInteractor(private val eventITRepository: EventRepository): ConferenceUseCase {

    override fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>> =
        eventITRepository.getConferenceCategory()


}