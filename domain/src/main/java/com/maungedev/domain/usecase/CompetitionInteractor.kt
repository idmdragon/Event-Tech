package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.repository.EventRepository

class CompetitionInteractor(private val eventITRepository: EventRepository): CompetitionUseCase {

    override fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>> =
        eventITRepository.getCompetitionCategory()

    override fun getAllEventCompetition(): Flow<Resource<List<Event>>> =
        eventITRepository.getAllEventCompetition()
}