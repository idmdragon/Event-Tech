package com.maungedev.data.source.remote.service

import com.maungedev.data.constant.FirebaseConstant
import com.maungedev.data.source.remote.FirebaseResponse
import com.maungedev.data.source.remote.response.UserResponse
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

    fun addSchedule(id: String): Flow<FirebaseResponse<UserResponse>> =
        flow {

            addArrayStringValueAtDocField(
                FirebaseConstant.FirebaseCollection.USER,
                getCurrentUserId(),
                FirebaseConstant.Field.SCHEDULE,
                id
            )

            emitAll(
                getDocument<UserResponse>(
                    FirebaseConstant.FirebaseCollection.USER,
                    getCurrentUserId()
                )
            )

        }

    fun addFavoriteEvent(id: String): Flow<FirebaseResponse<UserResponse>> =
        flow {

            addArrayStringValueAtDocField(
                FirebaseConstant.FirebaseCollection.USER,
                getCurrentUserId(),
                FirebaseConstant.Field.FAVORITE,
                id
            )

            emitAll(
                getDocument<UserResponse>(
                    FirebaseConstant.FirebaseCollection.USER,
                    getCurrentUserId()
                )
            )

        }
}