package com.maungedev.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.detail.DetailUseCase
import com.maungedev.domain.utils.Resource

class DetailViewModel(private val useCase: DetailUseCase) : ViewModel() {

    fun getEventById(uid: String): LiveData<Resource<Event>> =
        useCase.getEventById(uid).asLiveData()

    fun addSchedule(id: String): LiveData<Resource<Unit>> =
        useCase.addSchedule(id).asLiveData()

    fun addFavorite(id: String): LiveData<Resource<Unit>> =
        useCase.saveEvent(id).asLiveData()
}
