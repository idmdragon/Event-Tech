package com.maungedev.favorite.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.usecase.favorite.FavoriteUseCase
import com.maungedev.domain.utils.Resource

class FavoriteViewModel (private val useCase: FavoriteUseCase) : ViewModel() {

    fun getAllFavorite(ids: List<String>): LiveData<Resource<List<Event>>> =
        useCase.getAllFavorite(ids).asLiveData()

    fun getCurrentUser(): LiveData<Resource<User>> =
        useCase.getCurrentUser().asLiveData()
}