package me.inassar.demos.socialapp.presentation.chatRoom

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.domain.usecase.GetRoomHistoryUseCase

class ChatRoomViewModel(private val getRoomHistoryUseCase: GetRoomHistoryUseCase) : ViewModel() {

    private val _chatHistoryState = mutableStateOf(ChatRoomHistoryState())
    val chatHistoryState: State<ChatRoomHistoryState> = _chatHistoryState

    fun getChatHistory(receiver: String) {
        _chatHistoryState.value = ChatRoomHistoryState(loading = true)
        viewModelScope.launch {
            getRoomHistoryUseCase(receiver).collect {
                when (it) {
                    is ResponseResource.Error -> _chatHistoryState.value =
                        ChatRoomHistoryState(error = it.errorMessage.errorMessage.orEmpty())
                    is ResponseResource.Success -> _chatHistoryState.value =
                        ChatRoomHistoryState(data = it.data.roomData.orEmpty())
                }
            }
        }
    }

}