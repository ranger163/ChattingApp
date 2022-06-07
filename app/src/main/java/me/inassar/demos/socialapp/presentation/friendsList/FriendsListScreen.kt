package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
    val friendListState = viewModel.friendListState.value

    if (friendListState.error.isNotEmpty())
        Dialog(message = friendListState.error, confirmBtnText = "Login again") {
            viewModel.performLogout(navController)
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .padding(8.dp), onClick = {
                        viewModel.performLogout(navController)
                    }) {
                    Text(text = "Logout")
                }
            }
            if (friendListState.data.isNotEmpty())
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    val friendList = friendListState.data

                    friendList.forEach {
                        item {
                            FriendListItemRow(friendData = it,navController)
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
