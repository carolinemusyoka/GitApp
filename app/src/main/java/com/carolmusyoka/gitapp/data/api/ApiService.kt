package com.carolmusyoka.gitapp.data.api

import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse
import com.carolmusyoka.gitapp.data.model.GetUserFollowingResponse
import com.carolmusyoka.gitapp.data.model.GetUserRepositoriesResponse
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    // get user
    @GET("users/{user}")
    suspend fun getUser(
        @Path("user") user: String): GetUserResponse

    //Get user followers
    @GET("users/{user}/followers")
    suspend fun getUserFollowers(
        @Path("user") user: String): GetUserFollowersResponse

    // Get users followed by user
    @GET("users/{user}/following")
    suspend fun getUserFollowedByUser(
        @Path("user") user: String): GetUserFollowingResponse

    // Get users repositories
    @GET("users/{user}/repos")
    suspend fun getUserRepositories(
        @Path("user") user: String): GetUserRepositoriesResponse
}