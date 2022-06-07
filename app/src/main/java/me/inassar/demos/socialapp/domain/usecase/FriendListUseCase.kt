package me.inassar.demos.socialapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.domain.mapper.toFriendList
import me.inassar.demos.socialapp.domain.model.friendList.FriendList
import me.inassar.demos.socialapp.domain.repository.ChatRepository

class FriendListUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(): Flow<ResponseResource<FriendList>> = flow {
        repository.getFriendList().collect {
            val responseResource = when (it) {
                is ResponseResource.Error -> ResponseResource.error(it.errorMessage.toFriendList())
                is ResponseResource.Success -> ResponseResource.success(it.data.toFriendList())
            }
            emit(responseResource)
        }
    }
}