package com.acchao.portfolio.ui.home


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acchao.portfolio.data.Portfolio
import com.acchao.portfolio.data.PortfolioRepository
import com.acchao.portfolio.data.Skill
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    portfolioRepository: PortfolioRepository
) : ViewModel() {

    val portfolio: StateFlow<ResumeUiState> = portfolioRepository.getPortfolio()
        .map(ResumeUiState::Success)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ResumeUiState.Loading
        )

    private val filteredSkills: MutableList<Skill> = mutableListOf()
    private val _filteredSkills: MutableState<List<Skill>> = mutableStateOf(filteredSkills)

    fun addFilter(skill: Skill) {
        filteredSkills.add(skill)
    }

    sealed interface ResumeUiState {
        data object Loading : ResumeUiState
        data class Success(val portfolio: Portfolio) : ResumeUiState
    }
}