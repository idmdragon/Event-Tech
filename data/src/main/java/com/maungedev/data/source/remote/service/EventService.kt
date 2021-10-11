package com.maungedev.data.source.remote.service

import com.maungedev.data.constant.FirebaseConstant.FirebaseCollection.COMPETITION_CATEGORY_COLLECTION
import com.maungedev.data.constant.FirebaseConstant.FirebaseCollection.CONFERENCE_CATEGORY_COLLECTION
import com.maungedev.data.source.remote.FirebaseResponse
import com.maungedev.data.source.remote.response.CompetitionCategoryResponse
import com.maungedev.data.source.remote.response.ConferenceCategoryResponse
import kotlinx.coroutines.flow.Flow

class EventService: FirebaseService() {

    fun getAllConferenceCategory(): Flow<FirebaseResponse<List<ConferenceCategoryResponse>>> =
        getCollection(CONFERENCE_CATEGORY_COLLECTION)

    fun getAllCompetitionCategory(): Flow<FirebaseResponse<List<CompetitionCategoryResponse>>> =
        getCollection(COMPETITION_CATEGORY_COLLECTION)
}