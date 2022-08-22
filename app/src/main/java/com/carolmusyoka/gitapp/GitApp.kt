package com.carolmusyoka.gitapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.carolmusyoka.gitapp.navigation.MainNavGraph
import com.carolmusyoka.gitapp.presentation.theme.GitAppTheme

@Composable
fun GitApp() {
    GitAppTheme {
        val navController = rememberNavController()
        MainNavGraph(
            navController = navController
        )
    }
}