package me.inassar.demos.socialapp.presentation.friendsList

import me.inassar.demos.socialapp.domain.model.friendList.FriendList

data class FriendListState(
    val isLoading: Boolean = false,
    val data: FriendList? = null,
    val error: FriendList? = null
)
