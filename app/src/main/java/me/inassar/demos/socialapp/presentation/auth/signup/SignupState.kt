package me.inassar.demos.socialapp.presentation.auth.signup

import me.inassar.demos.socialapp.domain.model.signup.SignupResponse

data class SignupState(
    val isLoading: Boolean = false,
    val data: SignupResponse? = null,
    val error: String = ""
)
