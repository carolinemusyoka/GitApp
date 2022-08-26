package com.carolmusyoka.gitapp.util

import com.carolmusyoka.gitapp.data.local.Bookmarks
import com.carolmusyoka.gitapp.data.model.GetUserResponse

sealed class UserEvent {
    data class SearchUser(val search: String) : UserEvent()
    data class SetSearchText(val search: String) : UserEvent()
    data class SetValidationError(val message: String) : UserEvent()
    object ClearSearchText : UserEvent()
}

fun GetUserResponse.toBookmarks(): Bookmarks {
    return Bookmarks().apply {
        this.avatar_url = this@toBookmarks.avatar_url
        this.bio = this@toBookmarks.bio
        this.blog = this@toBookmarks.blog
        this.company = this@toBookmarks.company
        this.created_at = this@toBookmarks.created_at
        this.email = this@toBookmarks.email
        this.events_url = this@toBookmarks.events_url
        this.followers = this@toBookmarks.followers
        this.followers_url = this@toBookmarks.followers_url
        this.following = this@toBookmarks.following
        this.following_url = this@toBookmarks.following_url
        this.gists_url = this@toBookmarks.gists_url
        this.gravatar_id = this@toBookmarks.gravatar_id
        this.hireable = this@toBookmarks.hireable
        this.html_url = this@toBookmarks.html_url
        this.id = this@toBookmarks.id
        this.location = this@toBookmarks.location
        this.login = this@toBookmarks.login
        this.name = this@toBookmarks.name
        this.node_id = this@toBookmarks.node_id
        this.organizations_url = this@toBookmarks.organizations_url
        this.public_gists = this@toBookmarks.public_gists
        this.public_repos = this@toBookmarks.public_repos
        this.received_events_url =this@toBookmarks.received_events_url
        this.repos_url = this@toBookmarks.repos_url
        this.site_admin = this@toBookmarks.site_admin
        this.starred_url = this@toBookmarks.starred_url
        this.subscriptions_url = this@toBookmarks.subscriptions_url
        this.twitter_username = this@toBookmarks.twitter_username
        this.type = this@toBookmarks.type
        this.updated_at = this@toBookmarks.updated_at
        this.url = this@toBookmarks.url

    }
}