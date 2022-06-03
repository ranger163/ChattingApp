package me.inassar.demos.socialapp.di

import me.inassar.demos.socialapp.presentation.auth.login.LoginViewModel
import me.inassar.demos.socialapp.presentation.auth.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
}