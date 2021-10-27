package com.maungedev.domain.usecase.competition

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.repository.EventRepository

class CompetitionInteractor(private val eventRepository: EventRepository): CompetitionUseCase {

    override fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>> =
        eventRepository.getCompetitionCategory()

    override fun getAllEventCompetition(): Flow<Resource<List<Event>>> =
        eventRepository.getAllEventCompetition()

    override fun getEventCompetitionByCategories(categories: String): Flow<Resource<List<Event>>> =
        eventRepository.getEventsCompetitionByCategories(categories)
}