package com.acchao.portfolio.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

private const val PortfolioUserPreferences = "user_preferences"

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

    fun getPortfolio(): Flow<Portfolio> = flow {
        emit(portfolio)
    }

    suspend fun setHasSeenOnboarding(hasSeen: Boolean) {
        context.dataStore.edit {
            it[SEEN_SPLASH_KEY] = hasSeen
        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }
}