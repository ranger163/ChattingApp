package me.inassar.demos.socialapp.domain.model.signup

data class SignupResponse(
    val token: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val errorMessage: String? = null
)
