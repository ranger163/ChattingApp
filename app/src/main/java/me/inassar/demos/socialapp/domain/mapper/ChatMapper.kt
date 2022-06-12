package me.inassar.demos.socialapp.domain.mapper

import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto
import me.inassar.demos.socialapp.domain.model.chatRoom.RoomHistoryList
import me.inassar.demos.socialapp.domain.model.friendList.FriendList
import java.text.DateFormat
import java.util.*

fun FriendListResponseDto.toFriendList(): FriendList {
    val friendList = arrayListOf<FriendList.FriendInfo>()
    data?.forEach {
        friendList.add(
            FriendList.FriendInfo(
                token = it?.token.orEmpty(),
                username = it?.friendInfo?.username.orEmpty(),
                email = it?.friendInfo?.email.orEmpty(),
                avatar = it?.friendInfo?.avatar.orEmpty(),
                lastMessage = FriendList.FriendInfo.LastMessage(
                    textMessage = it?.friendInfo?.lastMessage?.textMessage,
                    timestamp = it?.friendInfo?.lastMessage?.timestamp
                )
            )
        )
    }
    return FriendList(
        friendInfo = friendList,
        errorMessage = error?.message
    )
}

fun ChatRoomResponseDto.toRoomHistoryList(): RoomHistoryList {
    val roomHistoryList = arrayListOf<RoomHistoryList.RoomHistory>()
    data?.forEach {

        val date = Date(it?.timestamp ?: 0L)
        val formattedDate = DateFormat
            .getDateInstance(DateFormat.DEFAULT)
            .format(date)

        roomHistoryList.add(
            RoomHistoryList.RoomHistory(
                receiver = it?.receiver.orEmpty(),
                sender = it?.sender.orEmpty(),
                textMessage = it?.textMessage.orEmpty(),
                timestamp = formattedDate
            )
        )
    }
    return RoomHistoryList(
        roomData = roomHistoryList,
        errorMessage = error?.message
    )
}