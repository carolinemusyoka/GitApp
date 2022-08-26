package com.carolmusyoka.gitapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.carolmusyoka.gitapp.presentation.screens.UserDetailsScreen
import com.carolmusyoka.gitapp.presentation.viewmodel.UserViewModel

sealed class DashDestinations(val route: String) {
    object HomeRoute : DashDestinations("home")
    object UserDetail: DashDestinations("{user}/detail")

    fun createRoute(user:String) = "$user/detail"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = DashDestinations.HomeRoute.route,
    userViewModel: UserViewModel = hiltViewModel()
){

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        navigation(
            route = DashDestinations.HomeRoute.route,
            startDestination = HomeTabs.HOME.route
        ){
            addHomeGraph(
                navController = navController,
                navToDetails = {},
                modifier = modifier
            )
        }
        composable(
            route = DashDestinations.UserDetail.route
        ){ navBackStackEntry ->
            val userArg = navBackStackEntry.arguments?.getString("user") ?: ""
            val user = remember{
                userViewModel.getUser(userArg)
                userViewModel.user
            }.collectAsState()

            user.value.data?.let { UserDetailsScreen(userResponse = it, navBack = {navController.navigateUp()}) }
        }
    }
}