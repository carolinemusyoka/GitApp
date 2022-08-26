package com.carolmusyoka.gitapp.data.uistates

import com.carolmusyoka.gitapp.data.model.GetUserFollowingResponse

data class FollowingUiState(
    val isLoading: Boolean = false,
    val data: GetUserFollowingResponse? = null,
    val error: Boolean = false
)