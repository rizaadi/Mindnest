package com.zephysus.mindnest.feature.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zephysus.mindnest.core.data.repository.HabitRepository
import com.zephysus.mindnest.core.model.data.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * A sealed hierarchy describing the state of the feed of news resources.
 */
sealed interface HabitUiState {
    data object Loading : HabitUiState
    data class Success(
        val habit: List<Habit>,
    ) : HabitUiState
}

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
) : ViewModel() {
    val habitUiState: StateFlow<HabitUiState> =
        habitRepository.getHabitsStream().map<List<Habit>, HabitUiState>(HabitUiState::Success)
            .onStart { emit(HabitUiState.Loading) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HabitUiState.Loading,
            )
}
