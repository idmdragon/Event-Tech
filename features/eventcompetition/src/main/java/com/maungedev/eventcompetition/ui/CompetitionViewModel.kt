package com.maungedev.eventcompetition.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.competition.CompetitionUseCase
import com.maungedev.domain.utils.Resource

class CompetitionViewModel(private val useCase: CompetitionUseCase) : ViewModel() {

    fun getCompetitionCategory(): LiveData<Resource<List<CompetitionCategory>>> =
        useCase.getCompetitionCategory().asLiveData()

    fun getAllCompetitionEvent():  LiveData<Resource<List<Event>>> =
        useCase.getAllEventCompetition().asLiveData()
}