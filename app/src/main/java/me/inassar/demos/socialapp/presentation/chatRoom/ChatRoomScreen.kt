package me.inassar.demos.socialapp.presentation.chatRoom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ChatRoomScreen(
    navController: NavHostController,
    friendName: String,
    friendEmail: String,
    friendAvatar: String
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { navController.popBackStack() }) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "$friendName\n$friendEmail\n$friendAvatar"
        )
    }
}
