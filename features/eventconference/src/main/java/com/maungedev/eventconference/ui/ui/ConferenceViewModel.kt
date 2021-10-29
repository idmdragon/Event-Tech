package com.maungedev.eventconference.ui.ui

import androidx.lifecycle.*
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.conference.ConferenceUseCase
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.launch

class ConferenceViewModel(private val useCase: ConferenceUseCase) : ViewModel() {

    private val _sortType = MutableLiveData<String>()
    val sortType : LiveData<String> = _sortType

    fun setSortType(sortType: String) = viewModelScope.launch {
        _sortType.value = sortType
    }

    fun getConferenceCategory(): LiveData<Resource<List<ConferenceCategory>>> =
        useCase.getConferenceCategory().asLiveData()

    fun getAllConferenceEvent():  LiveData<Resource<List<Event>>> =
        useCase.getAllEventConference().asLiveData()

    fun getAllPopularEvent():  LiveData<Resource<List<Event>>> =
        useCase.getAllPopularEvent().asLiveData()

    fun refreshAllEvent():  LiveData<Resource<Unit>> =
        useCase.refreshAllEvent().asLiveData()

    fun getEventByCategories(categories: String):  LiveData<Resource<List<Event>>> =
        useCase.getEventConferenceByCategories(categories).asLiveData()


}