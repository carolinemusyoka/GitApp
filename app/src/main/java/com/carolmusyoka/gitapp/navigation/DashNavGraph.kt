package com.carolmusyoka.gitapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

object DashDestinations{
    const val HOME_ROUTE = "home"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = DashDestinations.HOME_ROUTE,
    openDrawer: () -> Unit,
){

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        navigation(
            route = DashDestinations.HOME_ROUTE,
            startDestination = HomeTabs.HOME.route
        ){
            addHomeGraph(
                navController = navController,
                navToDetails = {},
                modifier = modifier,
                openDrawer = openDrawer
            )
        }
    }
}