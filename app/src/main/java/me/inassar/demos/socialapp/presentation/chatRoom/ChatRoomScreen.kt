package me.inassar.demos.socialapp.presentation.chatRoom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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
    val lifecycleOwner = LocalLifecycleOwner.current
    val chatState = viewModel.chatState.value
    val loggedInUser = viewModel.getUserInfo()

    LaunchedEffect(key1 = true) {
        viewModel.getChatHistory(friendEmail)
    }

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START)
                viewModel.connectToSocket(
                    sender = loggedInUser?.email.orEmpty(),
                    receiver = friendEmail
                )
            else if (event == Lifecycle.Event.ON_STOP)
                viewModel.disconnectSocket()
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
            val groupByTimestampHistoryList = chatState.data.groupBy { it.formattedDate }

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

        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            border = BorderStroke(
                width = 0.5.dp,
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.background
            )
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.messageText.value,
                onValueChange = viewModel::onMessageChange,
                placeholder = {
                    Text(
                        text = "Type message...",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                    )
                },
                singleLine = false,
                maxLines = 4,
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                    disabledIndicatorColor = MaterialTheme.colorScheme.background,
                    containerColor = MaterialTheme.colorScheme.background
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { viewModel.sendMessage() }
                            .rotate(-45f),
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send message",
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                    )
                }
            )
        }

    }
}


