package me.inassar.demos.socialapp.data.remote.source

import io.ktor.client.*
import io.ktor.client.request.*
import me.inassar.demos.socialapp.common.ENDPOINT_CHAT_HISTORY
import me.inassar.demos.socialapp.common.ENDPOINT_FRIEND_LIST
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto

class ChatRemoteImpl(private val client: HttpClient) : ChatRemote {

    override suspend fun getFriendList(token: String?): ResponseResource<FriendListResponseDto> =
        try {
            val response = client.get<FriendListResponseDto>(ENDPOINT_FRIEND_LIST) {
                header(
                    "Authorization",
                    "Bearer $token"
                )
            }
            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(FriendListResponseDto(error = FriendListResponseDto.Error("Oops, something bad happened :(")))
        }

    override suspend fun getRoomHistory(
        token: String?,
        receiver: String
    ): ResponseResource<ChatRoomResponseDto> =
        try {
            val response = client.get<ChatRoomResponseDto>(ENDPOINT_CHAT_HISTORY) {
                parameter("receiver", receiver)
                header(
                    "Authorization",
                    "Bearer $token"
                )
            }

            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(ChatRoomResponseDto(error = ChatRoomResponseDto.Error("Oops, something bad happened :(")))
        }
}