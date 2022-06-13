package me.inassar.demos.socialapp.data.remote.source

import kotlinx.coroutines.flow.Flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.MessageResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto

interface ChatRemote {
    suspend fun getFriendList(token: String?): ResponseResource<FriendListResponseDto>
    suspend fun getRoomHistory(
        token: String?,
        receiver: String
    ): ResponseResource<ChatRoomResponseDto>

    suspend fun connectToSocket(
        sender: String,
        receiver: String,
        token: String
    ): ResponseResource<String>

    suspend fun sendMessage(message: String)
    fun receiveMessage(): Flow<MessageResponseDto>
    suspend fun disconnectSocket()
}