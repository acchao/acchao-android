package com.acchao.portfolio.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreRepository(context: Context) {

    private object PreferencesKey {
        val seenSplashKey = booleanPreferencesKey("has_seen_splash")
    }

    private val dataStore = context.dataStore

    fun hasSeenOnboarding() : Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val onBoardingState = preferences[PreferencesKey.seenSplashKey] ?: false
            onBoardingState
        }
    }

    suspend fun setHasSeenOnboarding(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.seenSplashKey] = completed
        }
    }
}