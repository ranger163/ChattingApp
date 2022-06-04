package me.inassar.demos.socialapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.data.remote.dto.friendList.response.FriendListResponseDto
import me.inassar.demos.socialapp.data.remote.source.ChatRemote
import me.inassar.demos.socialapp.domain.repository.ChatRepository

class ChatRepositoryImpl(private val remote: ChatRemote, private val sessionPrefs: SessionPrefs) :
    ChatRepository {
    override suspend fun getFriendList(): Flow<ResponseResource<FriendListResponseDto>> =
        flow {
            when (val response = remote.getFriendList(sessionPrefs.getUser()?.token)) {
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }
}