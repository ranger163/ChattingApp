package me.inassar.demos.socialapp.common

import me.inassar.demos.socialapp.domain.model.user.User

interface SessionPrefs {
    fun saveUser(user: User)
    fun getUser(): User?
    fun clearSession()
}