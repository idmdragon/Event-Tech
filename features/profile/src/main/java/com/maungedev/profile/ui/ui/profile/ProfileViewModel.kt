package com.maungedev.profile.ui.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.usecase.profile.ProfileUseCase

class ProfileViewModel(private val useCase: ProfileUseCase) : ViewModel() {

    fun getCurrentUser() = useCase.getCurrentUser().asLiveData()

    fun updateUsername(username: String) = useCase.updateUsername(username).asLiveData()

}