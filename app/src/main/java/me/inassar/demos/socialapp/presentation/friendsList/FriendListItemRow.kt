package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import me.inassar.demos.socialapp.common.ENDPOINT_AVATAR
import me.inassar.demos.socialapp.common.getTimeAgo
import me.inassar.demos.socialapp.common.navigateTo
import me.inassar.demos.socialapp.domain.model.friendList.FriendList
import me.inassar.demos.socialapp.presentation.common.Routes

@Composable
fun FriendListItemRow(friendData: FriendList.FriendInfo, navController: NavController) {
    val args = "${friendData.username}/${friendData.email}/${friendData.avatar}"

    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                navigateTo(
                    navController = navController,
                    "${Routes.ChatRoom.route}/$args"
                )
            }
            .height(60.dp)
    ) {
        Image(
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = MaterialTheme.colorScheme.onBackground),
            painter = rememberAsyncImagePainter(model = ENDPOINT_AVATAR + friendData.avatar + ".png"),
            contentDescription = "Friend avatar"
        )

        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(start = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = friendData.username,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = if (friendData.lastMessage?.timestamp != null) getTimeAgo(friendData.lastMessage.timestamp) else "",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                text = friendData.lastMessage?.textMessage ?: "...",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 11.sp
                ),
                maxLines = 1
            )
        }
    }
}