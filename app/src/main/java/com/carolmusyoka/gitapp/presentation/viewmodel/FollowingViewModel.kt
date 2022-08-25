package com.carolmusyoka.gitapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolmusyoka.gitapp.data.uistates.FollowersUiState
import com.carolmusyoka.gitapp.data.uistates.FollowingUiState
import com.carolmusyoka.gitapp.domain.usecases.GetFollowingUseCase
import com.carolmusyoka.gitapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FollowingViewModel @Inject constructor(private val followingUseCase: GetFollowingUseCase): ViewModel() {
    private val _followingResponse: MutableStateFlow<FollowingUiState> = MutableStateFlow(
        FollowingUiState(true, null, false)
    )
    val following get() = _followingResponse

    fun getFollowing(username: String) {
        viewModelScope.launch {
            _followingResponse.emit(FollowingUiState(true, null, false))
            when (val response = followingUseCase.invoke(username)){
                is NetworkResult.Success -> {
                    _followingResponse.emit(FollowingUiState(false, response.data, false))
                }
                is NetworkResult.Failure -> {
                    _followingResponse.emit(FollowingUiState(false, null, true))
                }
            }
        }
    }
}