package me.inassar.demos.socialapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.domain.mapper.toRoomHistoryList
import me.inassar.demos.socialapp.domain.model.chatRoom.RoomHistoryList
import me.inassar.demos.socialapp.domain.repository.ChatRepository

class GetRoomHistoryUseCase(private val repository: ChatRepository) {

    suspend operator fun invoke(receiver: String):
            Flow<ResponseResource<RoomHistoryList>> = flow {
        repository.getRoomHistory(receiver).collect {
            val responseResource = when (it) {
                is ResponseResource.Error -> ResponseResource.error(it.errorMessage.toRoomHistoryList())
                is ResponseResource.Success -> ResponseResource.success(it.data.toRoomHistoryList())
            }
            emit(responseResource)
        }
    }
}