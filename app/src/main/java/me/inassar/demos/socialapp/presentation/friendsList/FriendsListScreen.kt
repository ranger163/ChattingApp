package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import me.inassar.demos.socialapp.R
import me.inassar.demos.socialapp.presentation.common.Dialog
import me.inassar.demos.socialapp.presentation.common.Loader
import org.koin.androidx.compose.getViewModel

@Composable
fun FriendsListScreen(
    navController: NavController,
    viewModel: FriendListViewModel = getViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val friendListState = viewModel.friendListState.value

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.getFriendList()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (friendListState.error.isNotEmpty())
        Dialog(message = friendListState.error, confirmBtnText = "Login again") {
            viewModel.performLogout(navController)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(8.dp), onClick = {
                        viewModel.performLogout(navController)
                    }) {
                    Text(text = "Logout")
                }

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Chats",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                    )
                },
                placeholder = { Text(text = "Search User") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                value = viewModel.searchState.value,
                onValueChange = {
                    viewModel.onSearchTextChange(it)
                })

            if (friendListState.data.isNotEmpty())
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp)
                ) {
                    val friendList = friendListState.data

                    friendList.forEach {
                        item {
                            FriendListItemRow(friendData = it, navController)
                        }
                    }
                }
        }

        if (friendListState.data.isEmpty())
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(42.dp),
                painter = painterResource(id = R.drawable.bg_friend_list_empty),
                contentDescription = "No friends!"
            )

        Loader(isLoading = friendListState.isLoading)
    }
}
