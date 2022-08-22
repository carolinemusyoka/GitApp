package com.carolmusyoka.gitapp.data.repository

import com.carolmusyoka.gitapp.data.api.ApiService
import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse
import com.carolmusyoka.gitapp.data.model.GetUserFollowingResponse
import com.carolmusyoka.gitapp.data.model.GetUserRepositoriesResponse
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import com.carolmusyoka.gitapp.util.NetworkResult
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService): MainRepository, BaseRepository() {
    override suspend fun getUser(user: String): NetworkResult<GetUserResponse> {
        return safeApiCall {
            apiService.getUser(user)
        }
    }

    override suspend fun getUserRepos(user: String): NetworkResult<GetUserRepositoriesResponse> {
        return safeApiCall {
            apiService.getUserRepositories(user)
        }
    }

    override suspend fun getUserFollowers(user: String): NetworkResult<GetUserFollowersResponse> {
        return safeApiCall {
            apiService.getUserFollowers(user)
        }
    }

    override suspend fun getUserFollowing(user: String): NetworkResult<GetUserFollowingResponse> {
        return safeApiCall {
            apiService.getUserFollowedByUser(user)
        }
    }

}