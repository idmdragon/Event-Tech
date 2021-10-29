package com.maungedev.eventcompetition.ui

import androidx.lifecycle.*
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.competition.CompetitionUseCase
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.launch

class CompetitionViewModel(private val useCase: CompetitionUseCase) : ViewModel() {

    private val _sortType = MutableLiveData<String>()
    val sortType : LiveData<String> = _sortType

    fun setSortType(sortType: String) = viewModelScope.launch {
        _sortType.value = sortType
    }

    fun getCompetitionCategory(): LiveData<Resource<List<CompetitionCategory>>> =
        useCase.getCompetitionCategory().asLiveData()

    fun getAllCompetitionEvent():  LiveData<Resource<List<Event>>> =
        useCase.getAllEventCompetition().asLiveData()

    fun getEventsByCategories(categories: String):  LiveData<Resource<List<Event>>>  {
        return if (categories == "Semua"){
            useCase.getAllEventCompetition().asLiveData()
        }else{
            useCase.getEventCompetitionByCategories(categories).asLiveData()
        }
    }

    fun refreshAllEvent():  LiveData<Resource<Unit>> =
        useCase.refreshAllEvent().asLiveData()

}