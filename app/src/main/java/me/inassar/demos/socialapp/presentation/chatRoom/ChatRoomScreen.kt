package me.inassar.demos.socialapp.presentation.chatRoom

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatRoomScreen(
    navController: NavHostController,
    friendName: String,
    friendEmail: String,
    friendAvatar: String,
    viewModel: ChatRoomViewModel = getViewModel()
) {
    val chatHistoryState = viewModel.chatHistoryState.value
    val loggedInUser = viewModel.getUserInfo()

    LaunchedEffect(key1 = true) {
        viewModel.getChatHistory(friendEmail)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        Box(Modifier.fillMaxWidth()) {

            OutlinedIconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(8.dp),

                ) {
                Icon(
                    modifier = Modifier.padding(start = 8.dp),
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "Back button"
                )
            }

            Text(
                modifier = Modifier.align(Alignment.Center),
                text = friendName,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            reverseLayout = true
        ) {
            val groupByTimestampHistoryList = chatHistoryState.data.groupBy { it.formattedDate }

            groupByTimestampHistoryList.forEach { (date, messages) ->
                items(messages) { message ->
                    val isSender = message.sender == loggedInUser?.email
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = if (isSender)
                            Alignment.CenterEnd
                        else
                            Alignment.CenterStart
                    ) {
                        MessageBubble(
                            message = message,
                            isSender = isSender,
                            loggedInUser?.avatar.orEmpty(),
                            friendAvatar
                        )
                    }
                }
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = date.orEmpty(),
                            style = MaterialTheme.typography.bodySmall
                                .copy(color = MaterialTheme.colorScheme.onBackground)
                        )
                    }
                }
            }
        }

    }
}


