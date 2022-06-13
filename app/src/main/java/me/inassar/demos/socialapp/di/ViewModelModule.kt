package me.inassar.demos.socialapp.di

import me.inassar.demos.socialapp.presentation.auth.login.LoginViewModel
import me.inassar.demos.socialapp.presentation.auth.signup.SignupViewModel
import me.inassar.demos.socialapp.presentation.chatRoom.ChatRoomViewModel
import me.inassar.demos.socialapp.presentation.friendsList.FriendListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignupViewModel(get(), get()) }
    viewModel { FriendListViewModel(get(), get()) }
    viewModel { ChatRoomViewModel(get(), get(), get()) }
}