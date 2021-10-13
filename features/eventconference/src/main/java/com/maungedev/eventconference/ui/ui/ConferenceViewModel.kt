package com.maungedev.eventconference.ui.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.ConferenceUseCase
import com.maungedev.domain.utils.Resource

class ConferenceViewModel(private val useCase: ConferenceUseCase) : ViewModel() {

    fun getConferenceCategory(): LiveData<Resource<List<ConferenceCategory>>> =
        useCase.getConferenceCategory().asLiveData()

    fun getAllConferenceEvent():  LiveData<Resource<List<Event>>> =
        useCase.getAllEventConference().asLiveData()
}