package com.maungedev.eventconference.ui.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.domain.usecase.ConferenceUseCase
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.dummy.DummyData

class ConferenceViewModel(private val useCase: ConferenceUseCase) : ViewModel() {

    fun getConferenceCategory(): LiveData<Resource<List<ConferenceCategory>>> =
        useCase.getConferenceCategory().asLiveData()

}