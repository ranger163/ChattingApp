package me.inassar.demos.socialapp.domain.model.signup

data class SignupResponse(
    val token: String? = null,
    val username: String? = null,
    val errorMessage: String? = null
)
