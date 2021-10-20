package com.maungedev.profile.ui.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.usecase.ProfileUseCase

class ProfileViewModel(private val useCase: ProfileUseCase) : ViewModel() {

    fun getCurrentUser() = useCase.getCurrentUser().asLiveData()

}