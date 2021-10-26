package com.maungedev.domain.usecase.search

import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    fun searchConference(title:String):Flow<Resource<List<Event>>>
    fun searchCompetition(title:String):Flow<Resource<List<Event>>>
}