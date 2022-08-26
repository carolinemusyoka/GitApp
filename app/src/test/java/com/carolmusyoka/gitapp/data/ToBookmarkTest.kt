package com.carolmusyoka.gitapp.data

import com.carolmusyoka.gitapp.data.local.Bookmarks
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import com.carolmusyoka.gitapp.util.toBookmarks
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileInputStream

@RunWith(MockitoJUnitRunner::class)
class ToBookmarkTest {

    private val gson = Gson()
    private lateinit var user: GetUserResponse

    @Before
    fun setup() {
        openMocks(this)
        val userBytes = FileInputStream("src/main/assets/user.json").readBytes()
        user = gson.fromJson(String(userBytes), GetUserResponse::class.java)
    }

    @Test
    fun test_that_data_is_converted_ans_is_equal() {
        val bookmark = user.toBookmarks()
        val expected = GetUserResponse(
            login = "carolinemusyoka",
             id = 44951692,
            node_id = "MDQ6VXNlcjQ0OTUxNjky",
            avatar_url = "https://avatars.githubusercontent.com/u/44951692?v=4",
            gravatar_id = "",
            url = "https://api.github.com/users/carolinemusyoka",
            html_url = "https://github.com/carolinemusyoka",
            followers_url = "https://api.github.com/users/carolinemusyoka/followers",
            following_url = "https://api.github.com/users/carolinemusyoka/following{/other_user}",
            gists_url = "https://api.github.com/users/carolinemusyoka/gists{/gist_id}",
            starred_url = "https://api.github.com/users/carolinemusyoka/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/carolinemusyoka/subscriptions",
            organizations_url = "https://api.github.com/users/carolinemusyoka/orgs",
            repos_url = "https://api.github.com/users/carolinemusyoka/repos",
            events_url = "https://api.github.com/users/carolinemusyoka/events{/privacy}",
            received_events_url = "https://api.github.com/users/carolinemusyoka/received_events",
            type = "User",
            site_admin = false,
            name = "Carol",
            company = null,
            blog = "",
            location = "Kenya",
            email = null,
            hireable = true,
            bio = "Android Developer🚀 Technical Writer.",
            twitter_username = "carolmusyoka_",
            public_repos = 30,
            public_gists = 31,
            followers = 391,
            following = 74,
            created_at = "2018-11-11T20:06:56Z",
            updated_at = "2022-08-26T11:41:13Z"
        ).toBookmarks()
        assertEquals(expected.login, bookmark.login)
    }
}