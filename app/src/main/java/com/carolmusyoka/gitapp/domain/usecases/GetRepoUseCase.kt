package com.carolmusyoka.gitapp.domain.usecases

import com.carolmusyoka.gitapp.data.model.GetUserFollowersResponse
import com.carolmusyoka.gitapp.data.model.GetUserRepositoriesResponse
import com.carolmusyoka.gitapp.data.repository.MainRepository
import com.carolmusyoka.gitapp.util.NetworkResult
import javax.inject.Inject

class GetRepoUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend fun invoke(username: String): NetworkResult<GetUserRepositoriesResponse> = mainRepository.getUserRepos(username)
}