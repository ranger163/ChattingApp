package me.inassar.demos.socialapp.presentation.common

sealed class Routes(val route: String) {
    object Splash : Routes("Splash")
    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object FriendsList : Routes("FriendsList")
    object ChatRoom : Routes("ChatRoom") {
        const val ARG_FRIEND_NAME = "friendName"
        const val ARG_FRIEND_EMAIL = "friendEmail"
        const val ARG_FRIEND_AVATAR = "friendAvatar"
        const val ARGS = "/{$ARG_FRIEND_NAME}/{$ARG_FRIEND_EMAIL}/{$ARG_FRIEND_AVATAR}"
    }
}