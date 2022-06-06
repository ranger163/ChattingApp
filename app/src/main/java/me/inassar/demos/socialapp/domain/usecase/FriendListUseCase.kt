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
            when (it) {
//                is ResponseResource.Error -> emit(ResponseResource.error(FriendList(errorMessage = it.errorMessage.error)))
//                is ResponseResource.Success -> emit(ResponseResource.success(it.data.data?.map { it?.toFriendList() }))
                is ResponseResource.Error -> emit(ResponseResource.error(it.errorMessage.toFriendList()))
                is ResponseResource.Success -> emit(ResponseResource.success(it.data.toFriendList()))
            }
        }
    }
}