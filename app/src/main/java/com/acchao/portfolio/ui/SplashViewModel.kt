package com.acchao.portfolio.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acchao.portfolio.data.DataStoreRepository
import com.acchao.portfolio.navigation.Home
import com.acchao.portfolio.navigation.Splash
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Splash.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.hasSeenOnboarding().collect { completed ->
                if (completed) {
                    _startDestination.value = Home.route
                } else {
                    _startDestination.value = Splash.route
                }
            }
        }

        viewModelScope.launch {
            // Note(Andrew): There is some bug with the latest versions of compose navigation
            // where start destination is way slower. Using an older version (<2.7.0) + a delay here
            // helps hide the fact that start_destination hasn't posted to MutableState yet.
            delay(100)
            _isLoading.value = false
        }

    }
}