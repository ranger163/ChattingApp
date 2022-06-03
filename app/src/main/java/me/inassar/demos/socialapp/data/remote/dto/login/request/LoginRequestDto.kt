package me.inassar.demos.socialapp.data.remote.dto.login.request

@kotlinx.serialization.Serializable
data class LoginRequestDto(
    val email: String? = null,
    val password: String? = null
)
