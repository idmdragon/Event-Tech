package com.maungedev.eventtech.ui.main.ui.conference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.dummy.DummyData

class ConferenceViewModel : ViewModel() {

    private val _listEvent = MutableLiveData<List<EventIT>>()

    fun getPopularEvent() : LiveData<List<EventIT>> {
        _listEvent.value = DummyData.generateDummyEvent()
        return _listEvent
    }

    private val _listCategory = MutableLiveData<List<ConferenceCategory>>()

    fun getConferenceCategory(): LiveData<List<ConferenceCategory>>{
        _listCategory.value = DummyData.generateConferenceCategory()
        return _listCategory
    }

}