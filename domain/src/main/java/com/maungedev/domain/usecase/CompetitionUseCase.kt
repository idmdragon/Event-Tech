package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.Event

interface CompetitionUseCase {
    fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>>
    fun getAllEventCompetition(): Flow<Resource<List<Event>>>
}