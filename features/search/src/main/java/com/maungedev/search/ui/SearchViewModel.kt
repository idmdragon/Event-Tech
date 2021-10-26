package com.maungedev.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.search.SearchUseCase
import com.maungedev.domain.utils.Resource

class SearchViewModel(private val useCase: SearchUseCase) : ViewModel() {

    fun searchConference(title: String): LiveData<Resource<List<Event>>> =
        useCase.searchConference(title).asLiveData()

    fun searchCompetition(title: String): LiveData<Resource<List<Event>>> =
        useCase.searchCompetition(title).asLiveData()


}