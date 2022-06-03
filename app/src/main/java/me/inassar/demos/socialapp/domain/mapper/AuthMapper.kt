package me.inassar.demos.socialapp.domain.mapper

import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto
import me.inassar.demos.socialapp.domain.model.login.LoginResponse

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    token = data?.token,
    username = data?.user?.username,
    errorMessage = error?.message
)