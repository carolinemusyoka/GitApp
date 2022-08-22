package com.carolmusyoka.gitapp.data.uistates

import com.carolmusyoka.gitapp.data.model.GetUserRepositoriesResponse

data class RepoUiState(
    val isLoading: Boolean = false,
    val data: GetUserRepositoriesResponse? = null,
    val error: Boolean = false
)