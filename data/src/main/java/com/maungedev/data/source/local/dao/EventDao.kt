package com.maungedev.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maungedev.data.source.local.entity.CompetitionCategoryEntity
import com.maungedev.data.source.local.entity.ConferenceCategoryEntity
import com.maungedev.data.source.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConferenceCategory(conferenceCategoryEntity: List<ConferenceCategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitionCategory(competitionCategoryEntity: List<CompetitionCategoryEntity>)

    @Query("SELECT * FROM conference_category")
    fun selectAllConferenceCategory(): Flow<List<ConferenceCategoryEntity>>

    @Query("SELECT * FROM competition_category")
    fun selectAllCompetitionCategory(): Flow<List<CompetitionCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEvent(event: List<EventEntity>)

    @Query("SELECT * FROM event WHERE eventType = :eventType")
    fun selectAllEventByType(eventType: String): Flow<List<EventEntity>>

    @Query("SELECT * FROM event WHERE eventType = :eventType ORDER BY numbersOfView DESC LIMIT 10")
    fun selectAllPopular(eventType: String): Flow<List<EventEntity>>

    @Query("SELECT * FROM event WHERE eventType = :eventType and eventCategory = :eventCategories")
    fun selectEventByCategories(eventType: String, eventCategories: String): Flow<List<EventEntity>>

    @Query("SELECT * FROM event WHERE uid = :uid")
    fun selectEventByUid(uid: String): Flow<EventEntity>

    @Query("SELECT * FROM event WHERE uid in (:ids) LIMIT 10")
    fun selectAllMySchedules(ids: List<String>): Flow<List<EventEntity>>

    @Query("SELECT * FROM event WHERE uid in (:ids) LIMIT 10")
    fun selectAllMyFavorite(ids: List<String>): Flow<List<EventEntity>>


}