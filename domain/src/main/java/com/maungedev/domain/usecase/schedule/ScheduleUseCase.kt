package com.maungedev.domain.usecase.schedule

import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ScheduleUseCase {
    fun getAllSchedule(ids: List<String>):Flow<Resource<List<Event>>>
    fun getCurrentUser(): Flow<Resource<User>>
}