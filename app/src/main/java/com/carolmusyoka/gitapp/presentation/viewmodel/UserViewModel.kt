package com.carolmusyoka.gitapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.SemanticsProperties.Error
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolmusyoka.gitapp.data.uistates.RepoUiState
import com.carolmusyoka.gitapp.data.uistates.UserUiState
import com.carolmusyoka.gitapp.domain.usecases.GetUserUseCase
import com.carolmusyoka.gitapp.util.NetworkResult
import com.carolmusyoka.gitapp.util.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: GetUserUseCase): ViewModel() {

    private val _state = mutableStateOf(UserUiState())
    val state: State<UserUiState> = _state
    private var searchJob: Job? = null
    private var lastSearch = ""

    private val _username = mutableStateOf("")
    val userSearch: State<String> = _username

    fun setUsername(value: String) {
        _username.value = value
    }

    init {
        searchUser("")
    }

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.SearchUser -> {
                viewModelScope.launch {
                    if (event.search.isNotBlank()){
                        searchUser(event.search)
                    }
                }
            }
            is UserEvent.ClearSearchText -> {
                _state.value = _state.value.copy(
                    searchString = ""
                )
            }
            is UserEvent.SetSearchText -> {
                _state.value = _state.value.copy(
                    searchString = event.search
                )
            }
        }
    }

    private fun searchUser(search: String) {
        // Stop recurring calls for search
        if (lastSearch == search) return
        // Cancel the old Job if it is not completed
        searchJob?.cancel()
        // Create search Job
        searchJob = viewModelScope.launch {
            when (val result = userUseCase.invoke(search)) {
                is NetworkResult.Success -> {
                    _state.value = UserUiState(false, result.data)
                }
                is NetworkResult.Failure -> {
                    _state.value = UserUiState(true, null, true)
                }
            }
        }
    }
    private val _userResponse: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState(true, null, false))
    val user get() = _userResponse

    fun getUser(username: String) {
        viewModelScope.launch {
            _userResponse.emit(UserUiState(true, null, false))
            when (val response = userUseCase.invoke(username)) {
                is NetworkResult.Success -> {
                    _userResponse.emit(UserUiState(false, response.data, false))
                }
                is NetworkResult.Failure -> {
                    _userResponse.emit(UserUiState(false, null, true))
                }
            }
        }
    }
}