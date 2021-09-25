package com.maungedev.eventconference.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maungedev.domain.model.EventConferenceCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.dummy.DummyData

class ConferenceViewModel : ViewModel() {

    private val _listEvent = MutableLiveData<List<EventIT>>()

    fun getPopularEvent() : LiveData<List<EventIT>> {
        _listEvent.value = DummyData.generateDummyEvent()
        return _listEvent
    }

    private val _listCategory = MutableLiveData<List<EventConferenceCategory>>()

    fun getConferenceCategory(): LiveData<List<EventConferenceCategory>>{
        _listCategory.value = DummyData.generateConferenceCategory()
        return _listCategory
    }

}