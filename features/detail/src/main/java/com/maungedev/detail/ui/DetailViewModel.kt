package com.maungedev.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.Event
import com.maungedev.domain.usecase.DetailUseCase
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class DetailViewModel(private val useCase: DetailUseCase) : ViewModel() {

    fun getEventById(uid: String): LiveData<Resource<Event>> =
        useCase.getEventById(uid).asLiveData()

}
