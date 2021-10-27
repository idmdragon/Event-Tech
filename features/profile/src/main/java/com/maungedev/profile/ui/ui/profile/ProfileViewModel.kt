package com.maungedev.profile.ui.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.maungedev.domain.model.User
import com.maungedev.domain.usecase.profile.ProfileUseCase
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel(private val useCase: ProfileUseCase) : ViewModel() {

    fun getCurrentUser(): LiveData<Resource<User>> =
        useCase.getCurrentUser().asLiveData()

    fun updateUsername(username: String) : LiveData<Resource<Unit>> =
        useCase.updateUsername(username).asLiveData()

    fun logout(): Unit =
        useCase.logout()


}