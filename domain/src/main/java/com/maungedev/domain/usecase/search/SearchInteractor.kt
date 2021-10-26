package com.maungedev.domain.usecase.search

import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.repository.EventRepository
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


class SearchInteractor (private val eventRepository: EventRepository): SearchUseCase {
    override fun searchConference(title: String): Flow<Resource<List<Event>>> =
        eventRepository.searchConference(title)

    override fun searchCompetition(title: String): Flow<Resource<List<Event>>> =
        eventRepository.searchCompetition(title)
}