package com.maungedev.data.source.remote.service

import com.maungedev.data.constant.FirebaseConstant
import com.maungedev.data.source.remote.FirebaseResponse
import com.maungedev.data.source.remote.response.CompetitionCategoryResponse
import com.maungedev.data.source.remote.response.ConferenceCategoryResponse
import com.maungedev.data.source.remote.response.EventResponse
import kotlinx.coroutines.flow.Flow

class EventService: FirebaseService() {

    fun getAllConferenceCategory(): Flow<FirebaseResponse<List<ConferenceCategoryResponse>>> =
        getCollection( FirebaseConstant.FirebaseCollection.CONFERENCE_CATEGORY_COLLECTION)

    fun getAllCompetitionCategory(): Flow<FirebaseResponse<List<CompetitionCategoryResponse>>> =
        getCollection( FirebaseConstant.FirebaseCollection.COMPETITION_CATEGORY_COLLECTION)

    fun getAllEvent(): Flow<FirebaseResponse<List<EventResponse>>> =
        getCollection( FirebaseConstant.FirebaseCollection.EVENT_COLLECTION)

    fun getEventById(id: String): Flow<FirebaseResponse<EventResponse>> =
        getDocument( FirebaseConstant.FirebaseCollection.EVENT_COLLECTION,id)

    fun getMySchedule(ids: List<String>): Flow<FirebaseResponse<List<EventResponse>>> =
        getDocumentsWhereIds(
            FirebaseConstant.FirebaseCollection.EVENT_COLLECTION,
            FirebaseConstant.Field.UID,
            ids
        )
}