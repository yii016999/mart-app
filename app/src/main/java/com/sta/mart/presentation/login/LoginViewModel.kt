package com.sta.mart.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sta.mart.data.fake.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {

    // UI state management, using StateFlow for reactive updates
    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)

    // Exposing the state as a read-only StateFlow
    val state: StateFlow<LoginState> = _state.asStateFlow()

    sealed class LoginState {
        data object Idle : LoginState()
        data object Loading : LoginState()
        data object Success : LoginState()
        data class Error(val message: String) : LoginState()

    }

    fun login(email: String, password: String) {
        // Kotlin coroutines for asynchronous operations
        // Using viewModelScope to launch a coroutine tied to the ViewModel's lifecycle
        viewModelScope.launch {
            // Before starting the login process, set the state to Loading
            _state.value = LoginState.Loading
            // Call the login method from the repository, which returns a Result type
            // Input email and password, and handle a Result
            val result = authRepository.login(email, password)
            // Update the state based on the result of the login operation
            _state.value = if (result.isSuccess) {
                LoginState.Success
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}