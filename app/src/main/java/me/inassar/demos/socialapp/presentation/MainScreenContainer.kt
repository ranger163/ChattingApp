package me.inassar.demos.socialapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.inassar.demos.socialapp.presentation.common.Routes
import me.inassar.demos.socialapp.presentation.auth.forgotPassword.ForgotPasswordScreen
import me.inassar.demos.socialapp.presentation.auth.login.LoginScreen
import me.inassar.demos.socialapp.presentation.auth.signup.SignUpScreen
import me.inassar.demos.socialapp.presentation.chatRoom.ChatRoomScreen
import me.inassar.demos.socialapp.presentation.friendsList.FriendsListScreen
import me.inassar.demos.socialapp.presentation.splash.SplashScreen

@Composable
fun MainScreenContainer() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
        composable(Routes.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Routes.FriendsList.route) {
            FriendsListScreen(navController)
        }
        composable(Routes.ChatRoom.route) {
            ChatRoomScreen(navController)
        }
    }
}