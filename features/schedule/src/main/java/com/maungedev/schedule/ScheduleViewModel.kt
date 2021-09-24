package com.maungedev.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.dummy.DummyData

class ScheduleViewModel : ViewModel() {

    private val _listEvent = MutableLiveData<List<EventIT>>()

    fun getScheduleEvent() : LiveData<List<EventIT>> {
        _listEvent.value = DummyData.generateDummyEvent()
        return _listEvent
    }
}