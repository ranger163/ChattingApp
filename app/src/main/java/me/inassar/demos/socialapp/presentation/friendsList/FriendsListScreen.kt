package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import me.inassar.demos.socialapp.common.Routes
import me.inassar.demos.socialapp.common.navigateTo

@Composable
fun FriendsListScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { navigateTo(navController, Routes.ChatRoom.route) }) {
        Text(modifier = Modifier.align(Alignment.Center), text = "Friends List")
    }
}