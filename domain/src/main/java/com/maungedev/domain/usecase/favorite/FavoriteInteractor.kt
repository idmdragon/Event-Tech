package com.maungedev.domain.usecase.favorite

import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.repository.EventRepository
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow


class FavoriteInteractor (private val eventRepository: EventRepository): FavoriteUseCase {
    override fun getAllFavorite(ids: List<String>): Flow<Resource<List<Event>>> =
        eventRepository.getAllFavorite(ids)

    override fun deleteFavoriteEvent(id: String): Flow<Resource<Unit>> =
        eventRepository.deleteFavoriteEvent(id)

    override fun getCurrentUser(): Flow<Resource<User>> =
        eventRepository.getCurrentUser()


}