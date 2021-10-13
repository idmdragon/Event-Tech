package com.maungedev.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maungedev.domain.model.Event
import com.maungedev.eventtech.dummy.DummyData

class ScheduleViewModel : ViewModel() {

    private val _listEvent = MutableLiveData<List<Event>>()

    fun getScheduleEvent() : LiveData<List<Event>> {
        _listEvent.value = DummyData.generateDummyEvent()
        return _listEvent
    }
}