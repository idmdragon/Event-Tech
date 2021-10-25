package com.maungedev.detail.ui

import androidx.lifecycle.*
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.usecase.detail.DetailUseCase
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: DetailUseCase) : ViewModel() {

    private val _isFavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isFavorited

    private val _isRemindered = MutableLiveData<Boolean>()
    val isRemindered: LiveData<Boolean> = _isRemindered

    fun setFavoriteState(state: Boolean) = viewModelScope.launch {
        _isFavorited.value = state
    }

    fun setReminderState(state: Boolean) = viewModelScope.launch {
        _isRemindered.value = state
    }

    fun getEventById(uid: String): LiveData<Resource<Event>> =
        useCase.getEventById(uid).asLiveData()

    fun addSchedule(id: String): LiveData<Resource<Unit>> =
        useCase.addSchedule(id).asLiveData()

    fun deleteSchedule(id: String): LiveData<Resource<Unit>> =
        useCase.deleteSchedule(id).asLiveData()

    fun addFavorite(id: String): LiveData<Resource<Unit>> =
        useCase.addFavoriteEvent(id).asLiveData()

    fun deleteFavorite(id: String): LiveData<Resource<Unit>> =
        useCase.deleteFavoriteEvent(id).asLiveData()

    fun getCurrentUser(): LiveData<Resource<User>> =
        useCase.getCurrentUser().asLiveData()


}
