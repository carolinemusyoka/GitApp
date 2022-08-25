package com.carolmusyoka.gitapp.navigation

import androidx.compose.runtime.Composable
import com.carolmusyoka.gitapp.presentation.screens.FollowersScreen
import com.carolmusyoka.gitapp.presentation.screens.FollowingScreen
import com.carolmusyoka.gitapp.presentation.screens.GistsScreen
import com.carolmusyoka.gitapp.presentation.screens.RepositoriesScreen

typealias ComposableFun = @Composable () -> Unit

sealed class PagerItem(var title:String, var screen: ComposableFun) {
    object Followers: PagerItem("Followers", { FollowersScreen()})
    object Following: PagerItem("Following", { FollowingScreen() })
    object Repositories: PagerItem("Repos", { RepositoriesScreen() })
    object Gists: PagerItem("Gists", { GistsScreen()})
}