package com.carolmusyoka.gitapp.data.repository

import com.carolmusyoka.gitapp.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class BaseRepository {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
       return withContext(Dispatchers.IO){
           try{
               NetworkResult.Success(apiCall.invoke())
           } catch (throwable: Throwable){
               when(throwable){
                   is HttpException -> { NetworkResult.Failure(
                       false,
                       throwable.code(),
                       throwable.response()?.errorBody()
                   )}
                   else -> { NetworkResult.Failure(false, null, null)}
               }
           }
       }
    }
}