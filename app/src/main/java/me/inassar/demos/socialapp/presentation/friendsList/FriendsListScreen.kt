package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.common.navigateTo
import me.inassar.demos.socialapp.presentation.common.Routes
import org.koin.androidx.compose.inject

@Composable
fun FriendsListScreen(navController: NavController) {
    val sessionPrefs by inject<SessionPrefs>()

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { navigateTo(navController, Routes.ChatRoom.route) }) {
        val annotatedString = buildAnnotatedString {
            append("Welcome, ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) { append("${sessionPrefs.getUser()?.username}") }
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = annotatedString
        )
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            onClick = {
                sessionPrefs.clearSession()
                navigateTo(navController, Routes.Login.route, true)
            }) {
            Text(text = "Logout")
        }
    }
}