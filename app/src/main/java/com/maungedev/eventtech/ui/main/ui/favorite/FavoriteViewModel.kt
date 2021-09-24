package com.maungedev.eventtech.ui.main.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.dummy.DummyData

class FavoriteViewModel : ViewModel(){
    private val _listEvent = MutableLiveData<List<EventIT>>()

    fun getFavoriteEvent() : LiveData<List<EventIT>> {
        _listEvent.value = DummyData.generateDummyEvent()
        return _listEvent
    }
}