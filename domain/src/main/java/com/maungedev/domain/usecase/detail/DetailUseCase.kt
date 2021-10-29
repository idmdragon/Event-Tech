package com.maungedev.domain.usecase.detail

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User

interface DetailUseCase {
    fun getEventById(id:String): Flow<Resource<Event>>
    fun addSchedule(id: String):Flow<Resource<Unit>>
    fun deleteSchedule(id: String):Flow<Resource<Unit>>
    fun addFavoriteEvent(id: String):Flow<Resource<Unit>>
    fun deleteFavoriteEvent(id: String):Flow<Resource<Unit>>
    fun getCurrentUser():Flow<Resource<User>>
    fun increaseNumbersOfRegistrationClick(id:String):Flow<Unit>
    fun increaseNumbersOfView(id:String):Flow<Unit>

}