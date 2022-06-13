package me.inassar.demos.socialapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.ChatRoomResponseDto
import me.inassar.demos.socialapp.data.remote.dto.chatRoom.MessageResponseDto
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto
import me.inassar.demos.socialapp.data.remote.source.ChatRemote
import me.inassar.demos.socialapp.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val remote: ChatRemote,
    private val sessionPrefs: SessionPrefs
) : ChatRepository {

    override suspend fun getFriendList(): Flow<ResponseResource<FriendListResponseDto>> =
        flow {
            val responseResource =
                when (val response = remote.getFriendList(sessionPrefs.getUser()?.token)) {
                    is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
                    is ResponseResource.Success -> ResponseResource.success(response.data)
                }
            emit(responseResource)
        }

    override suspend fun getRoomHistory(
        receiver: String
    ): Flow<ResponseResource<ChatRoomResponseDto>> = flow {
        val responseResource =
            when (val response = remote.getRoomHistory(sessionPrefs.getUser()?.token, receiver)) {
                is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
                is ResponseResource.Success -> ResponseResource.success(response.data)
            }

        emit(responseResource)
    }

    override suspend fun connectToSocket(
        sender: String,
        receiver: String
    ): ResponseResource<String> {
        return when (val response =
            remote.connectToSocket(sender, receiver, sessionPrefs.getUser()?.token.orEmpty())) {
            is ResponseResource.Error -> ResponseResource.error(response.errorMessage)
            is ResponseResource.Success -> ResponseResource.success(response.data)
        }
    }

    override suspend fun sendMessage(message: String) {
        remote.sendMessage(message)
    }

    override fun receiveMessage(): Flow<MessageResponseDto> = remote.receiveMessage()

    override suspend fun disconnectSocket() {
        remote.disconnectSocket()
    }
}