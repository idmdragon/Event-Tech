package com.maungedev.data.source.local

import com.maungedev.data.source.local.dao.EventDao
import com.maungedev.data.source.local.dao.UserDao
import com.maungedev.data.source.local.entity.CompetitionCategoryEntity
import com.maungedev.data.source.local.entity.ConferenceCategoryEntity
import com.maungedev.data.source.local.entity.EventEntity
import com.maungedev.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val userDao: UserDao,
    private val eventDao: EventDao
) {
    suspend fun insertUser(userEntity: UserEntity): Unit =
        userDao.insertUser(userEntity)

    fun selectUser(): Flow<UserEntity> =
        userDao.selectCurrentUser()

    suspend fun insertConferenceCategory(conferenceCategoryEntity: List<ConferenceCategoryEntity>): Unit =
        eventDao.insertConferenceCategory(conferenceCategoryEntity)

    suspend fun insertCompetitionCategory(competitionCategoryEntity: List<CompetitionCategoryEntity>): Unit =
        eventDao.insertCompetitionCategory(competitionCategoryEntity)

    fun selectAllConferenceCategory(): Flow<List<ConferenceCategoryEntity>> =
        eventDao.selectAllConferenceCategory()

    fun selectAllCompetitionCategory(): Flow<List<CompetitionCategoryEntity>> =
        eventDao.selectAllCompetitionCategory()

    suspend fun insertEvent(event: EventEntity): Unit =
        eventDao.insertEvent(event)

    suspend fun insertEvents(event: List<EventEntity>): Unit =
        eventDao.insertAllEvent(event)

    fun selectAllEventConference(): Flow<List<EventEntity>> =
        eventDao.selectAllEventByType("conference")

    fun selectAllEventCompetition(): Flow<List<EventEntity>> =
        eventDao.selectAllEventByType("competition")

    fun selectEventByCategories(eventCategory: String): Flow<List<EventEntity>> =
        eventDao.selectEventByCategories("conference",eventCategory)

    fun selectEventByUid(uid: String): Flow<EventEntity> =
        eventDao.selectEventByUid(uid)

    fun selectAllMySchedule(ids: List<String>): Flow<List<EventEntity>> =
        eventDao.selectAllMySchedules(ids)
}