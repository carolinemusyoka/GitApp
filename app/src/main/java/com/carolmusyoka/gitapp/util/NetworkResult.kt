package com.carolmusyoka.gitapp.util

import okhttp3.ResponseBody

sealed class NetworkResult <out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : NetworkResult<Nothing>()
}