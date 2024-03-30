package com.acchao.portfolio.viewmodel


import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {
    var hasSeenSplash = mutableStateOf(false)
}