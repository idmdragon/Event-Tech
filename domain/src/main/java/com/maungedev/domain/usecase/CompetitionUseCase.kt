package com.maungedev.domain.usecase

import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import com.maungedev.domain.model.CompetitionCategory

interface CompetitionUseCase {
    fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>>
}