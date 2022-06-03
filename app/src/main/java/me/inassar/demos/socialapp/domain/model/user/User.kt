package me.inassar.demos.socialapp.domain.model.user

data class User(
    val username: String?,
    val email: String?,
    val token: String?,
    val isLoggedIn: Boolean? = false
)