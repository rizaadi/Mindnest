package com.zephysus.mindnest.feature.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zephysus.mindnest.core.data.repository.HabitRepository
import com.zephysus.mindnest.core.model.data.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject


@HiltViewModel
class CreateHabitViewModel @Inject constructor(
    private val habitRepository: HabitRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateHabitUiState())
    val uiState: StateFlow<CreateHabitUiState> = _uiState.asStateFlow()

    fun updateHabitName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    fun updateFrequency(newFrequency: String) {
        _uiState.update {
            it.copy(frequency = newFrequency)
        }
    }

    fun saveHabit() {
        if (_uiState.value.name.isBlank() || _uiState.value.frequency.isBlank()) {
            _uiState.update {
                it.copy(userMessage = R.string.feature_habit_empty_habit_message)
            }
            return
        }
        viewModelScope.launch {
            habitRepository.createHabit(
                Habit(
                    id = "0",
                    name = _uiState.value.name,
                    frequency = _uiState.value.frequency,
                    streak = 0,
                    lastCompleted = Clock.System.now(),
                    createdAt = Clock.System.now()
                )
            )
        }
    }

}

data class CreateHabitUiState(
    val name: String = "",
    val frequency: String = "",
    val userMessage: Int? = null,
)
