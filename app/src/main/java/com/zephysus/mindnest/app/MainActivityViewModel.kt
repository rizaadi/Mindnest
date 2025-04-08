package com.zephysus.mindnest.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zephysus.mindnest.core.data.repository.UserRepository
import com.zephysus.mindnest.core.model.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = userRepository.getUsersStream()
        .onStart {
            userRepository.getUsers()
        }
        .map { users ->
            if (users.isEmpty()) {
                val dummyUser = User(
                    id = "1",
                    name = "Jumbo",
                    email = "jumbo@gmail.com",
                    createdAt = Clock.System.now()
                )
                userRepository.createUser(dummyUser)
            }
            MainActivityUiState.Success(users.first())
        }.stateIn(
            scope = viewModelScope,
            initialValue = MainActivityUiState.Loading,
            started = SharingStarted.WhileSubscribed(5_000),
        )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(
        val user: User,
    ) : MainActivityUiState

    /**
     * Returns `true` if the state wasn't loaded yet and it should keep showing the splash screen.
     */
    fun shouldKeepSplashScreen() = this is Loading
}