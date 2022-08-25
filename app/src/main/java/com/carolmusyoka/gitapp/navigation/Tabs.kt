package com.carolmusyoka.gitapp.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.carolmusyoka.gitapp.presentation.screens.HomeScreen
import com.carolmusyoka.gitapp.presentation.screens.ProfileScreen
import com.carolmusyoka.gitapp.presentation.screens.SearchScreen

enum class HomeTabs(
    val title: String,
    val icon: ImageVector,
    val route: String
){
    HOME("Home", Icons.Rounded.Home, "tabs/home"),
    SEARCH("Search", Icons.Rounded.Search, "tabs/search"),
    PROFILE("Profile", Icons.Rounded.Person, "tabs/profile")
}

fun NavGraphBuilder.addHomeGraph(
    navController: NavController,
    navToDetails: () -> Unit,
    modifier: Modifier = Modifier
){
    composable(HomeTabs.HOME.route){
        HomeScreen(
            modifier = modifier,
            navController = navController,
        )
    }
    composable(HomeTabs.SEARCH.route){
        SearchScreen(

        )
    }
    composable(HomeTabs.PROFILE.route){
        ProfileScreen(

        )
    }
}

@Composable
fun GitAppBottomBar(
    navController: NavController,
    tabs: Array<HomeTabs>
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: HomeTabs.HOME.route

    val routes = remember {
        HomeTabs.values().map { it.route }
    }
    if (currentRoute in routes){
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface
        ) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = tab.icon, contentDescription = tab.title)},
                    label = { Text(text = tab.title) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (currentRoute != tab.route) {
                            navController.navigate(tab.route)
                        }
                    },
                    alwaysShowLabel = false
                )
            }

        }
    }
}

