package com.maungedev.data.repository

import com.maungedev.data.helper.NetworkBoundRequest
import com.maungedev.data.helper.NetworkBoundResource
import com.maungedev.data.mapper.*
import com.maungedev.data.source.local.LocalDataSource
import com.maungedev.data.source.remote.FirebaseResponse
import com.maungedev.data.source.remote.RemoteDataSource
import com.maungedev.data.source.remote.response.CompetitionCategoryResponse
import com.maungedev.data.source.remote.response.ConferenceCategoryResponse
import com.maungedev.data.source.remote.response.EventResponse
import com.maungedev.data.source.remote.response.UserResponse
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.model.User
import com.maungedev.domain.repository.EventRepository
import com.maungedev.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class EventRepositoryImpl(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) : EventRepository {

    override fun addSchedule(id: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.addSchedule(id)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())

        }.asFlow()

    override fun deleteSchedule(id: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.deleteSchedule(id)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())

        }.asFlow()


    override fun deleteFavoriteEvent(id: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.deleteFavorite(id)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())

        }.asFlow()

    override fun addFavoriteEvent(id: String): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<UserResponse>() {

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.addFavoriteEvent(id)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())

        }.asFlow()

    override fun getCurrentUser(): Flow<Resource<User>> =
        flow {
            val userId = getCurrentUserId()
            if (userId.isNotEmpty()) {
                emitAll(getUser(userId))
            }
        }

    override fun getUser(id: String): Flow<Resource<User>> =
        object : NetworkBoundResource<User, UserResponse>() {
            override fun loadFromDB(): Flow<User?> =
                local.selectUser().toFlowModel()

            override fun shouldFetch(data: User?): Boolean =
                data == null

            override suspend fun createCall(): Flow<FirebaseResponse<UserResponse>> =
                remote.getCurrentUser(id)

            override suspend fun saveCallResult(data: UserResponse) =
                local.insertUser(data.toEntity())
        }.asFlow()

    override fun getCurrentUserId(): String =
        remote.getCurrentUserId()

    override fun getEventsConferenceByCategories(categories: String): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectEventsConferenceByCategories(categories).toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())
        }.asFlow()

    override fun getEventsCompetitionByCategories(categories: String): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectEventsCompetitionByCategories(categories).toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())
        }.asFlow()

    override fun getEventById(id: String): Flow<Resource<Event>> =
        object : NetworkBoundResource<Event, EventResponse>() {
            override fun loadFromDB(): Flow<Event?> =
                local.selectEventByUid(id).toFlowModel()

            override fun shouldFetch(data: Event?): Boolean =
                data == null

            override suspend fun createCall(): Flow<FirebaseResponse<EventResponse>> =
                remote.getEventById(id)

            override suspend fun saveCallResult(data: EventResponse) =
                local.insertEvent(data.toEntity())
        }.asFlow()

    override fun getAllEventConference(): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectAllEventConference().toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) {
                local.clearEvent()
                local.insertEvents(data.toListEntity())
            }
        }.asFlow()

    override fun getAllPopularEvent(): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectAllPopular().toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())
        }.asFlow()

    override fun getAllEventCompetition(): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectAllEventCompetition().toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) {
                local.clearEvent()
                local.insertEvents(data.toListEntity())
            }

        }.asFlow()

    override fun getConferenceCategory(): Flow<Resource<List<ConferenceCategory>>> =
        object :
            NetworkBoundResource<List<ConferenceCategory>, List<ConferenceCategoryResponse>>() {
            override fun loadFromDB(): Flow<List<ConferenceCategory>?> =
                local.selectAllConferenceCategory().toConferenceCategoryListFlowModel()

            override fun shouldFetch(data: List<ConferenceCategory>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<ConferenceCategoryResponse>>> =
                remote.getAllConferenceCategory()

            override suspend fun saveCallResult(data: List<ConferenceCategoryResponse>) =
                local.insertConferenceCategory(data.toListConferenceCategoryEntity())
        }.asFlow()

    override fun getCompetitionCategory(): Flow<Resource<List<CompetitionCategory>>> =
        object :
            NetworkBoundResource<List<CompetitionCategory>, List<CompetitionCategoryResponse>>() {
            override fun loadFromDB(): Flow<List<CompetitionCategory>?> =
                local.selectAllCompetitionCategory().toCompetitionCategoryListFlowModel()

            override fun shouldFetch(data: List<CompetitionCategory>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<FirebaseResponse<List<CompetitionCategoryResponse>>> =
                remote.getAllCompetitionCategory()

            override suspend fun saveCallResult(data: List<CompetitionCategoryResponse>) =
                local.insertCompetitionCategory(data.toListCompetitionCategoryEntity())
        }.asFlow()

    override fun getAllSchedule(ids: List<String>): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectAllMySchedule(ids).toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                true

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getMySchedule(ids)

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())
        }.asFlow()


    override fun getAllFavorite(ids: List<String>): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.selectAllMyFavorite(ids).toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                true

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getMyFavorite(ids)

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())
        }.asFlow()


    override fun increaseNumbersOfRegistrationClick(id: String): Flow<Unit> =
        remote.increaseNumbersOfRegistrationClick(id)

    override fun increaseNumbersOfView(id: String): Flow<Unit> =
        remote.increaseNumbersOfNumbersOfView(id)

    override fun searchConference(title: String): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.searchConference(title).toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                true

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.searchConference(title)

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())

        }.asFlow()

    override fun searchCompetition(title: String): Flow<Resource<List<Event>>> =
        object : NetworkBoundResource<List<Event>, List<EventResponse>>() {
            override fun loadFromDB(): Flow<List<Event>?> =
                local.searchCompetition(title).toListFlowModel()

            override fun shouldFetch(data: List<Event>?): Boolean =
                true

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.searchCompetition(title)

            override suspend fun saveCallResult(data: List<EventResponse>) =
                local.insertEvents(data.toListEntity())

        }.asFlow()

    override fun refreshAllEvent(): Flow<Resource<Unit>> =
        object : NetworkBoundRequest<List<EventResponse>>() {

            override suspend fun createCall(): Flow<FirebaseResponse<List<EventResponse>>> =
                remote.getAllEvent()

            override suspend fun saveCallResult(data: List<EventResponse>) {
                local.clearEvent()
                local.insertEvents(data.toListEntity())
            }


    }.asFlow()

}