package com.maungedev.eventcompetition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maungedev.domain.model.EventCompetitionCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.dummy.DummyData

class CompetitionViewModel : ViewModel() {

    private val _listEvent = MutableLiveData<List<EventIT>>()

    fun getCompetitionEvent() : LiveData<List<EventIT>> {
        _listEvent.value = DummyData.generateDummyEvent()
        return _listEvent
    }
    private val _listCategory = MutableLiveData<List<EventCompetitionCategory>>()

    fun getCompetitionCategory(): LiveData<List<EventCompetitionCategory>>{
        _listCategory.value = DummyData.generateCompetitionCategory()
        return _listCategory
    }
}