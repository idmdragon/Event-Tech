package com.maungedev.data.source.remote.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.maungedev.data.source.remote.FirebaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

abstract class FirebaseService {

    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore

    fun getCurrentUserId() = auth.currentUser?.uid

    fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<FirebaseResponse<String>> =
        flow {
            val createUser = auth.createUserWithEmailAndPassword(email, password).await()
            val user = createUser.user
            if (user != null) {
                emit(FirebaseResponse.Success(user.uid))
            } else {
                emit(FirebaseResponse.Empty)
            }
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)


    inline fun <RequestType, reified ResponseType> setDocument(
        collection: String,
        docId: String,
        document: RequestType
    ): Flow<FirebaseResponse<ResponseType>> =
        flow {
            firestore
                .collection(collection)
                .document(docId)
                .set(document as Any)
                .await()

            emitAll(getDocument<ResponseType>(collection, docId))
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    inline fun <reified ResponseType> getDocument(
        collection: String,
        docId: String
    ): Flow<FirebaseResponse<ResponseType>> =
        flow {
            val result = firestore
                .collection(collection)
                .document(docId)
                .get()
                .await()

            if (result.exists()) {
                emit(FirebaseResponse.Success(result.toObject(ResponseType::class.java)!!))
            } else {
                emit(FirebaseResponse.Empty)
            }
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)


    fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<FirebaseResponse<String>> =
        flow {
            val createUser = auth.signInWithEmailAndPassword(email, password).await()
            val user = createUser.user
            if (user != null) {
                emit(FirebaseResponse.Success(user.uid))
            } else {
                emit(FirebaseResponse.Empty)
            }
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    inline fun <reified ResponseType> getCollection(collection: String): Flow<FirebaseResponse<List<ResponseType>>> =
        flow {
            val result = firestore
                .collection(collection)
                .get()
                .await()

            if (result.isEmpty) {
                emit(FirebaseResponse.Empty)
            } else {
                emit(FirebaseResponse.Success(result.toObjects(ResponseType::class.java)))
            }
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)


    fun addArrayStringValueAtDocField(
        collection: String,
        docId: String,
        fieldName: String,
        value: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            firestore.collection(collection)
                .document(docId)
                .update(fieldName, FieldValue.arrayUnion(value))
                .await()
        }
    }

    fun removeArrayStringValueAtDocField(
        collection: String,
        docId: String,
        fieldName: String,
        value: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            firestore.collection(collection)
                .document(docId)
                .update(fieldName, FieldValue.arrayRemove(value))
                .await()
        }
    }

    inline fun <reified ResponseType> getDocumentsWhereIds(
        collection: String,
        fieldName: String,
        value: List<String>
    ): Flow<FirebaseResponse<List<ResponseType>>> =
        flow {
            val result = firestore
                .collection(collection)
                .whereIn(fieldName, value)
                .get()
                .await()

            if (result.isEmpty) {
                emit(FirebaseResponse.Empty)
            } else {
                emit(FirebaseResponse.Success(result.toObjects(ResponseType::class.java)))
            }
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)


    inline fun <RequestType, reified ResponseType> updateFieldInDocument(
        collection: String,
        docId: String,
        fieldName: String,
        value: String
    ): Flow<FirebaseResponse<ResponseType>> =
        flow {
            firestore
                .collection(collection)
                .document(docId)
                .update(fieldName, value)
                .await()

            emitAll(getDocument<ResponseType>(collection, docId))
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    fun increaseNumbersOfFieldInDocument(
        collection: String,
        docId: String,
        fieldName: String,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            firestore.collection(collection)
                .document(docId)
                .update(fieldName, FieldValue.increment(1))
                .await()
        }
    }

    inline fun <reified ResponseType> searchCollection(
        collection: String,
        whereField: String,
        query: String
    ): Flow<FirebaseResponse<List<ResponseType>>> =
        flow {
            val result = firestore
                .collection(collection)
                .orderBy(whereField)
                .startAt(query)
                .endAt(query + '\uf8ff')
                .get()
                .await()

            if (result.isEmpty) {
                emit(FirebaseResponse.Empty)
            } else {
                emit(FirebaseResponse.Success(result.toObjects(ResponseType::class.java)))
            }
        }.catch {
            emit(FirebaseResponse.Error(it.message.toString()))
        }.flowOn(Dispatchers.IO)

    fun signOut(): Unit = auth.signOut()
}
