package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.domain.usecase.FriendListUseCase

class FriendListViewModel(private val useCase: FriendListUseCase) : ViewModel() {

    private val _friendListState = mutableStateOf(FriendListState())
    val friendListState: State<FriendListState> = _friendListState

    init {
        getFriendList()
    }

    private fun getFriendList() {
        _friendListState.value = FriendListState(isLoading = true)
        viewModelScope.launch {
            useCase().collect {
                when (it) {
                    is ResponseResource.Error -> _friendListState.value =
                        FriendListState(error = it.errorMessage)
                    is ResponseResource.Success -> _friendListState.value =
                        FriendListState(data = it.data)
                }
            }
        }
    }
}