package com.maungedev.data.source.remote.service

import android.util.Log
import com.maungedev.data.constant.FirebaseConstant
import com.maungedev.data.source.remote.FirebaseResponse
import com.maungedev.data.source.remote.response.UserResponse
import com.maungedev.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class UserService : FirebaseService() {

    private fun getUser() = auth.currentUser

    fun isUserExist(): Flow<Boolean> = flow {
        if (getUser() != null) {
            emit(true)
        } else {
            emit(false)
        }
    }

    fun getUser(id: String): Flow<FirebaseResponse<UserResponse>> =
        getDocument(
            FirebaseConstant.FirebaseCollection.USER,
            id
        )

    fun addSchedule(id: String,userID: String): Flow<FirebaseResponse<UserResponse>> =
        flow {

            addArrayStringValueAtDocField(
                FirebaseConstant.FirebaseCollection.USER,
                userID,
                FirebaseConstant.Field.SCHEDULE,
                id
            )

            emitAll(
                getDocument<UserResponse>(
                    FirebaseConstant.FirebaseCollection.USER,
                    userID
                )
            )
        }

    fun addFavoriteEvent(id: String,userID: String): Flow<FirebaseResponse<UserResponse>> =
        flow {

            addArrayStringValueAtDocField(
                FirebaseConstant.FirebaseCollection.USER,
                userID,
                FirebaseConstant.Field.FAVORITE,
                id
            )

            emitAll(
                getDocument<UserResponse>(
                    FirebaseConstant.FirebaseCollection.USER,
                    userID
                )
            )

        }

    fun updateUsername(username: String,userID: String): Flow<FirebaseResponse<UserResponse>> =
        updateFieldInDocument<User, UserResponse>(
            FirebaseConstant.FirebaseCollection.USER,
            userID,
            FirebaseConstant.Field.USERNAME,
            username
        )

    fun deleteSchedule(id: String,userID: String):  Flow<FirebaseResponse<UserResponse>> =
        flow {
            Log.d("CEKKID"," deleteSchedule ${getCurrentUserId()}")
            removeArrayStringValueAtDocField(
                FirebaseConstant.FirebaseCollection.USER,
                userID,
                FirebaseConstant.Field.SCHEDULE,
                id
            )

            emitAll(
                getDocument<UserResponse>(
                    FirebaseConstant.FirebaseCollection.USER,
                    userID
                )
            )
        }

    fun deleteFavorite(id: String,userID: String): Flow<FirebaseResponse<UserResponse>>  =
        flow {

            removeArrayStringValueAtDocField(
                FirebaseConstant.FirebaseCollection.USER,
                userID,
                FirebaseConstant.Field.FAVORITE,
                id
            )

            emitAll(
                getDocument<UserResponse>(
                    FirebaseConstant.FirebaseCollection.USER,
                    userID
                )
            )
        }
}