package com.carolmusyoka.gitapp.data.repository

import androidx.lifecycle.LiveData
import com.carolmusyoka.gitapp.data.local.AppDatabase
import com.carolmusyoka.gitapp.data.local.Bookmarks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RoomRepository @Inject constructor(
    private val db: AppDatabase,
    private val coroutineContext: CoroutineContext

) {
    fun addBookmark(bookmark: Bookmarks) {
        CoroutineScope(this.coroutineContext).launch {
            db.bookmarksDao().insert(bookmark)
        }
    }

    val getBookmarks: LiveData<List<Bookmarks>>  = db.bookmarksDao().getBookmarks()

    fun checkBookmarked(login: String): LiveData<Boolean> {
        return db.bookmarksDao().contains(login)
    }
    suspend fun deleteBookmark(login: String) {
        db.bookmarksDao().deleteUser(login)
    }
    fun getLocalBookmark(id: Int) = db.bookmarksDao().getBookmarksById(id)

    suspend fun deleteAll() {
        CoroutineScope(this.coroutineContext).launch {
            db.bookmarksDao().deleteTable()
        }
    }
}