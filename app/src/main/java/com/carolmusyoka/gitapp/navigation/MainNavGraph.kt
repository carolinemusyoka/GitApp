package com.carolmusyoka.gitapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carolmusyoka.gitapp.presentation.screens.SplashScreen


object MainDestination {
    const val SPLASH_SCREEN_ROUTE = "splash"
}
enum class MainScreen(val route: String) {
    SPLASH("splash"),
    HOME("home")
}

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestination.SPLASH_SCREEN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.SPLASH.route,
    ){
        composable(
            route = MainScreen.SPLASH.route,
        ){
            SplashScreen(navToHomeScreen = {navController.navigate((DashDestinations.HomeRoute.route))})
        }
        composable(
            route = MainScreen.HOME.route
        ){
            DashboardNav(

            )

        }
    }
}