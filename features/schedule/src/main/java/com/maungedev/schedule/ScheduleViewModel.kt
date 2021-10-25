package com.maungedev.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.usecase.profile.ProfileUseCase
import com.maungedev.domain.usecase.schedule.ScheduleUseCase
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.dummy.DummyData
import kotlinx.coroutines.flow.Flow

class ScheduleViewModel(private val useCase: ScheduleUseCase) : ViewModel() {

    fun getAllSchedule(ids: List<String>): LiveData<Resource<List<Event>>> =
        useCase.getAllSchedule(ids).asLiveData()

    fun deleteSchedule(id: String): LiveData<Resource<Unit>> =
        useCase.deleteSchedule(id).asLiveData()

    fun getCurrentUser(): LiveData<Resource<User>> =
        useCase.getCurrentUser().asLiveData()
}