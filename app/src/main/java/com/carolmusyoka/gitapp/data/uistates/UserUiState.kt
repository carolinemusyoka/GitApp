package com.carolmusyoka.gitapp.data.uistates

import com.carolmusyoka.gitapp.data.model.GetUserResponse

data class UserUiState(
    val isLoading: Boolean = false,
    val data: GetUserResponse? = null,
    val error: Boolean = false,
    val searchString : String = ""
)