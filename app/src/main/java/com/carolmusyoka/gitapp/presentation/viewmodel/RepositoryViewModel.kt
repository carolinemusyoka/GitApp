package com.carolmusyoka.gitapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carolmusyoka.gitapp.data.uistates.RepoUiState
import com.carolmusyoka.gitapp.domain.usecases.GetRepoUseCase
import com.carolmusyoka.gitapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(private val getRepoUseCase: GetRepoUseCase ): ViewModel() {
    private val _repositoryResponse: MutableStateFlow<RepoUiState> = MutableStateFlow(RepoUiState(true, null, false))
    val repositories get() = _repositoryResponse

    fun getRepositories(username: String) {
        viewModelScope.launch {
            _repositoryResponse.emit(RepoUiState(true, null, false))
            when (val response = getRepoUseCase.invoke(username)) {
                is NetworkResult.Success -> {
                    _repositoryResponse.emit(RepoUiState(false, response.data, false))
                }
                is NetworkResult.Failure -> {
                    _repositoryResponse.emit(RepoUiState(false, null, true))
                }
            }
        }
    }
}
