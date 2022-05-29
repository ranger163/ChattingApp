package me.inassar.demos.socialapp.ui.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.inassar.demos.socialapp.common.Routes
import me.inassar.demos.socialapp.ui.auth.login.LoginScreen

@Composable
fun ScreenAuth() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
    }
}