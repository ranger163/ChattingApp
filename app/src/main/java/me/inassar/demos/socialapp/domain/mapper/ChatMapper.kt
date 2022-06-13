package me.inassar.demos.socialapp.domain.mapper

import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.MessageResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto
import me.inassar.demos.socialapp.domain.model.chatRoom.RoomHistoryList
import me.inassar.demos.socialapp.domain.model.friendList.FriendList
import java.text.SimpleDateFormat
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
    val roomHistoryList = arrayListOf<RoomHistoryList.Message>()
    data?.forEach {

        val (formattedDate, formattedTime) = dateTimeFormat(it?.timestamp)

        roomHistoryList.add(
            RoomHistoryList.Message(
                receiver = it?.receiver.orEmpty(),
                sender = it?.sender.orEmpty(),
                textMessage = it?.textMessage.orEmpty(),
                timestamp = it?.timestamp,
                formattedTime = formattedTime,
                formattedDate = formattedDate,
            )
        )
    }
    return RoomHistoryList(
        roomData = roomHistoryList,
        errorMessage = error?.message
    )
}

fun MessageResponseDto.toMessage(): RoomHistoryList.Message {

    val (formattedDate, formattedTime) = dateTimeFormat(timestamp)
    return RoomHistoryList.Message(
        sessionId=sessionId,
        textMessage = textMessage,
        sender = sender,
        receiver = receiver,
        timestamp = timestamp,
        formattedDate = formattedDate,
        formattedTime = formattedTime
    )

}

private fun dateTimeFormat(timestamp: Long?): Pair<String, String> {
    val date = Date(timestamp ?: 0L)
    val formattedDate = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(date)
    val formattedTime = SimpleDateFormat("hh:mm aa", Locale.ENGLISH).format(date)
    return Pair(formattedDate, formattedTime)
}