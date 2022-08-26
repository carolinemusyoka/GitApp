package com.carolmusyoka.gitapp.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "response_table", indices = [Index(value = ["login"], unique = true)])
open class GetUserResponse(
    var avatar_url: String? ="",
    var bio: String? ="",
    var blog: String? ="",
    var company: String? ="",
    var created_at: String? ="",
    var email: String? ="",
    var events_url: String? ="",
    var followers: Int? =0,
    var followers_url: String? ="",
    var following: Int? =0,
    var following_url: String? ="",
    var gists_url: String? ="",
    var gravatar_id: String? ="",
    var hireable: Boolean? =false,
    var html_url: String? ="",
    @PrimaryKey(autoGenerate = true)
    var id: Int? =0,
    var location: String? ="",
    var login: String? ="",
    var name: String? ="",
    var node_id: String? ="",
    var organizations_url: String? ="",
    var public_gists: Int? =0,
    var public_repos: Int? =0,
    var received_events_url: String? ="",
    var repos_url: String? ="",
    var site_admin: Boolean? =false,
    var starred_url: String? ="",
    var subscriptions_url: String? ="",
    var twitter_username: String? ="",
    var type: String?  ="",
    var updated_at: String? ="",
    var url: String? =""
){
    var bookmarked: Boolean = false
}