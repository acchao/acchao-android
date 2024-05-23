package com.acchao.portfolio.ui.welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acchao.portfolio.data.DataStoreRepository
import com.acchao.portfolio.navigation.Home
import com.acchao.portfolio.navigation.Splash
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Splash.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            dataStoreRepository.hasSeenOnboarding().collect {
                _startDestination.value = if (it) Home.route else Splash.route
                _isLoading.value = false
            }
        }
    }

    fun setHasSeenSplash(hasSeenSplash: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.setHasSeenOnboarding(hasSeenSplash)
        }
    }
}