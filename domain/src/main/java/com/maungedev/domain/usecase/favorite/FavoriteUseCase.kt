package com.maungedev.domain.usecase.favorite

import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllFavorite(ids: List<String>):Flow<Resource<List<Event>>>
    fun deleteFavoriteEvent(id: String):Flow<Resource<Unit>>
    fun getCurrentUser(): Flow<Resource<User>>
}