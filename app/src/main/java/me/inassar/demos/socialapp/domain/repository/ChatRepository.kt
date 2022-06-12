package me.inassar.demos.socialapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto

interface ChatRepository {
    suspend fun getFriendList(): Flow<ResponseResource<FriendListResponseDto>>
    suspend fun getRoomHistory(receiver: String): Flow<ResponseResource<ChatRoomResponseDto>>
}