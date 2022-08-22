package com.carolmusyoka.gitapp.domain.usecases

import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse
import com.carolmusyoka.gitapp.data.repository.MainRepository
import com.carolmusyoka.gitapp.util.NetworkResult
import javax.inject.Inject

class GetFollowersUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun invoke(username: String): NetworkResult<GetUserFollowersResponse> = mainRepository.getUserFollowers(username)
}