package me.inassar.demos.socialapp.common

import androidx.navigation.NavController

fun navigateTo(navController: NavController, destination: String, clearBackStack: Boolean = false) {
    navController.navigate(destination) {
        if (clearBackStack) popUpTo(0)
    }
}