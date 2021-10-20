package com.maungedev.data.source.remote

import com.maungedev.data.source.remote.response.CompetitionCategoryResponse
import com.maungedev.data.source.remote.response.ConferenceCategoryResponse
import com.maungedev.data.source.remote.response.EventResponse
import com.maungedev.data.source.remote.response.UserResponse
import com.maungedev.data.source.remote.service.AuthService
import com.maungedev.data.source.remote.service.EventService
import com.maungedev.data.source.remote.service.UserService
import com.maungedev.domain.model.User
import kotlinx.coroutines.flow.Flow

class RemoteDataSource(
    private val authService: AuthService,
    private val eventService: EventService,
    private val userService: UserService,
) {
    fun signUp(email:String, password:String, user: User) =
        authService.signUp(email,password,user)

    fun signIn(email:String, password:String) =
        authService.signIn(email,password)

    fun getAllConferenceCategory(): Flow<FirebaseResponse<List<ConferenceCategoryResponse>>> =
        eventService.getAllConferenceCategory()

    fun getAllCompetitionCategory(): Flow<FirebaseResponse<List<CompetitionCategoryResponse>>> =
        eventService.getAllCompetitionCategory()

    fun getAllEvent(): Flow<FirebaseResponse<List<EventResponse>>> =
        eventService.getAllEvent()

    fun getEventById(id: String): Flow<FirebaseResponse<EventResponse>> =
        eventService.getEventById(id)

    fun getCurrentUser(id: String): Flow<FirebaseResponse<UserResponse>> =
        userService.getUser(id)

    fun getCurrentUserId(): String =
        userService.getCurrentUserId()

    fun addSchedule(id: String): Flow<FirebaseResponse<UserResponse>> =
        userService.addSchedule(id)

    fun addFavoriteEvent(id: String): Flow<FirebaseResponse<UserResponse>> =
        userService.addFavoriteEvent(id)
}