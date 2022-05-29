package me.inassar.demos.socialapp.common

sealed class Routes(val route: String) {
    object Login : Routes("Login")
}