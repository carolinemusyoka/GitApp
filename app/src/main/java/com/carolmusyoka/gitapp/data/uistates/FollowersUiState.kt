package com.carolmusyoka.gitapp.data.uistates

import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse

data class FollowersUiState(
    val isLoading: Boolean = false,
    val data: GetUserFollowersResponse? = null,
    val error: Boolean = false
)