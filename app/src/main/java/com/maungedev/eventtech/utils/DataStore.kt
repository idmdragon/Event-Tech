package com.maungedev.eventtech.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

     companion object {
        val ONBOARD_PREF_KEY = booleanPreferencesKey("onboard")
        const val DATASTORE_NAME = "EventTech"

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var mInstance: com.maungedev.eventtech.utils.DataStore? = null

        fun getInstance(context: Context): com.maungedev.eventtech.utils.DataStore =
            mInstance ?: synchronized(this) {
                val newInstance = mInstance ?: DataStore(context)
                    .also { mInstance = it }
                newInstance
            }
    }

    private val Context.userPreferenceDataStore: DataStore<Preferences> by preferencesDataStore(
        name = DATASTORE_NAME
    )


    suspend fun savePrefHaveRunAppBefore(isFirstTime: Boolean) {
        context.userPreferenceDataStore.edit {
            it[ONBOARD_PREF_KEY] = isFirstTime
        }
    }


    fun readPrefHaveRunAppBefore(): Flow<Boolean> = context.userPreferenceDataStore.data
        .map {
            it[ONBOARD_PREF_KEY] != null
        }

}