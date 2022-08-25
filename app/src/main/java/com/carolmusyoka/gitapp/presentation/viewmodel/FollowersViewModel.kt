package com.carolmusyoka.gitapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolmusyoka.gitapp.data.uistates.FollowersUiState
import com.carolmusyoka.gitapp.domain.usecases.GetFollowersUseCase
import com.carolmusyoka.gitapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(private val followersUseCase: GetFollowersUseCase): ViewModel() {
    private val _getFollowersResponse: MutableStateFlow<FollowersUiState> = MutableStateFlow(FollowersUiState(true, null,false))
    val followers get() = _getFollowersResponse

    fun getFollowers(username: String){
        viewModelScope.launch {
            _getFollowersResponse.emit(FollowersUiState(true, null,false))
            when (val response = followersUseCase.invoke(username)) {
                is NetworkResult.Success -> {
                    _getFollowersResponse.emit(FollowersUiState(false, response.data,false))
                }
                is NetworkResult.Failure -> {
                    _getFollowersResponse.emit(FollowersUiState(false, null,true))
                }
            }
        }
    }
}