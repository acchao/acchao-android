package com.acchao.portfolio.viewmodel


import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    val hasSeenSplash: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}