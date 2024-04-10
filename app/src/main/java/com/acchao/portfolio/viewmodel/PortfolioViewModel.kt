package com.acchao.portfolio.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.acchao.portfolio.data.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val portfolioRepository: PortfolioRepository,
    private val application: Application
) : AndroidViewModel(application) {

    val seenSplash : MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        viewModelScope.launch {
            portfolioRepository.hasSeenOnboarding().collect {
                seenSplash.postValue(it)
            }
        }
    }

    fun setHasSeenOnboarding() {
        viewModelScope.launch {
            portfolioRepository.setHasSeenOnboarding(true)
        }
    }
}