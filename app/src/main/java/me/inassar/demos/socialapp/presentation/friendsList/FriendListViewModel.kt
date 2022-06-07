package me.inassar.demos.socialapp.presentation.friendsList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.common.navigateTo
import me.inassar.demos.socialapp.domain.usecase.FriendListUseCase
import me.inassar.demos.socialapp.presentation.common.Routes

class FriendListViewModel(
    private val useCase: FriendListUseCase,
    private val sessionPrefs: SessionPrefs
) : ViewModel() {

    private val _searchState = mutableStateOf("")
    val searchState: State<String> = _searchState

    private val _friendListState = mutableStateOf(FriendListState())
    val friendListState: State<FriendListState> = _friendListState

    init {
        getFriendList()
    }

    fun onSearchTextChange(result: String) {
        // TODO: I'm too lazy to handle search, on other hand you are the hero so have fun :D
        _searchState.value = result
    }

    fun performLogout(navController: NavController) {
        sessionPrefs.clearSession()
        navigateTo(navController, Routes.Login.route, true)
    }

    private fun getFriendList() {
        _friendListState.value = FriendListState(isLoading = true)
        viewModelScope.launch {
            useCase().onEach {
                when (it) {
                    is ResponseResource.Error ->
                        _friendListState.value =
                            FriendListState(error = it.errorMessage.errorMessage.orEmpty())
                    is ResponseResource.Success ->
                        _friendListState.value =
                            FriendListState(data = it.data.friendInfo.orEmpty())
                }
            }.launchIn(viewModelScope)
        }
    }
}