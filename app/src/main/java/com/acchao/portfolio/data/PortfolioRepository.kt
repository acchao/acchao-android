package com.acchao.portfolio.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

private const val USER_PREFERENCES_NAME = "user_preferences"

data class UserPreferences(val hasSeenOnboarding: Boolean)
class PortfolioRepository(private val context: Context) {

    companion object {
         val SEEN_SPLASH_KEY = booleanPreferencesKey("has_seen_splash")
    }

    // TODO(Andrew): research if Flow is the ideal type I want to return here
    fun hasSeenOnboarding() : Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[SEEN_SPLASH_KEY] ?: false
        }
    }

    suspend fun setHasSeenOnboarding(hasSeen: Boolean) {
        context.dataStore.edit {
            it[SEEN_SPLASH_KEY] = true
        }
    }
}