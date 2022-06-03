package me.inassar.demos.socialapp.presentation.auth.login

import me.inassar.demos.socialapp.domain.model.login.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val data: LoginResponse? = null,
    val error: String = ""
)
