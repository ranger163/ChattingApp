package me.inassar.demos.socialapp.presentation.common

sealed class Routes(val route: String) {
    object Splash : Routes("Splash")
    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object FriendsList : Routes("FriendsList")
    object ChatRoom : Routes("ChatRoom")
}