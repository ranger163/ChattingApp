package me.inassar.demos.socialapp.domain.mapper

import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto
import me.inassar.demos.socialapp.domain.model.friendList.FriendList

fun FriendListResponseDto.toFriendList(): FriendList {
    val friendList = arrayListOf<FriendList.FriendInfo>()
    data?.forEach {
        friendList.add(
            FriendList.FriendInfo(
                token = it?.token.orEmpty(),
                username = it?.friendInfo?.username.orEmpty(),
                email = it?.friendInfo?.email.orEmpty(),
                avatar = it?.friendInfo?.avatar.orEmpty(),
            )
        )
    }
    return FriendList(
        friendInfo = friendList,
        errorMessage = error?.message
    )
}