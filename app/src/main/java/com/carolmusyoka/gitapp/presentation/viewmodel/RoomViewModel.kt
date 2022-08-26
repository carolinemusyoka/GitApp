package com.carolmusyoka.gitapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import com.carolmusyoka.gitapp.data.repository.RoomRepository
import com.carolmusyoka.gitapp.util.toBookmarks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val roomRepository: RoomRepository): ViewModel() {

    fun getBookmark(id:  Int) = roomRepository.getLocalBookmark(id)
    val bookmarks = roomRepository.getBookmarks

    fun isBookmarked(login: String) = roomRepository.checkBookmarked(login)

    fun deleteBookmark(userResponse: GetUserResponse, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            roomRepository.deleteBookmark(userResponse.login ?: "")
        }
    }
    fun addBookmark(userResponse: GetUserResponse, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            roomRepository.addBookmark(userResponse.toBookmarks())
        }
    }

    suspend fun deleteAll() = roomRepository.deleteAll()

}