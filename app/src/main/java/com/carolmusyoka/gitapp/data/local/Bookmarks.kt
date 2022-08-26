package com.carolmusyoka.gitapp.data.local

import androidx.room.Entity
import com.carolmusyoka.gitapp.data.model.GetUserResponse

@Entity(tableName = "bookmarks_table", inheritSuperIndices = true)
class Bookmarks: GetUserResponse()

// to call Bookmars, use Bookmarks.getUserResponse()