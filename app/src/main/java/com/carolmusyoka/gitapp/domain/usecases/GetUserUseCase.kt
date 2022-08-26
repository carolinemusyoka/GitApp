package com.carolmusyoka.gitapp.domain.usecases

import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import com.carolmusyoka.gitapp.data.repository.MainRepository
import com.carolmusyoka.gitapp.util.NetworkResult
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun invoke(user: String): NetworkResult<GetUserResponse> = mainRepository.getUser(user)
}