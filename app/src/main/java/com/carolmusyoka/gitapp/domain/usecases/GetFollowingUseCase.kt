package com.carolmusyoka.gitapp.domain.usecases

import com.carolmusyoka.gitapp.data.model.GetUserFollowingResponse
import com.carolmusyoka.gitapp.data.repository.MainRepository
import com.carolmusyoka.gitapp.util.NetworkResult
import javax.inject.Inject

class GetFollowingUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun invoke(username: String): NetworkResult<GetUserFollowingResponse> = mainRepository.getUserFollowing(username)
}