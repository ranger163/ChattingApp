package me.inassar.demos.socialapp.data.remote.dto.signup.response


import kotlinx.serialization.Serializable

@Serializable
data class SignupResponseDto(
    val data: Data? = null,
    val error: Error? = null
) {
    @Serializable
    data class Data(
        val token: String? = null,
        val user: User? = null
    ) {
        @Serializable
        data class User(
            val email: String? = null,
            val username: String? = null,
            val avatar: String? = null,
        )
    }

    @Serializable
    data class Error(
        val message: String? = null
    )
}