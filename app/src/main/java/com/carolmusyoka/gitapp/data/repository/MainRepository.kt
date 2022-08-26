package com.carolmusyoka.gitapp.data.repository

import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse
import com.carolmusyoka.gitapp.data.model.GetUserFollowingResponse
import com.carolmusyoka.gitapp.data.model.GetUserRepositoriesResponse
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import com.carolmusyoka.gitapp.util.NetworkResult

interface MainRepository {
    // get user data
    suspend fun getUser(user: String): NetworkResult<GetUserResponse>
    // get user repos
    suspend fun getUserRepos(user: String): NetworkResult<GetUserRepositoriesResponse>
    // get user followers
    suspend fun getUserFollowers(user: String): NetworkResult<GetUserFollowersResponse>
    // get user following
    suspend fun getUserFollowing(user: String): NetworkResult<GetUserFollowingResponse>

}