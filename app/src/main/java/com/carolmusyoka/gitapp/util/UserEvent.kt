package com.carolmusyoka.gitapp.util

sealed class UserEvent {
    data class SearchUser(val search: String) : UserEvent()
    data class SetSearchText(val search: String) : UserEvent()
    data class SetValidationError(val message: String) : UserEvent()
    object ClearSearchText : UserEvent()
}