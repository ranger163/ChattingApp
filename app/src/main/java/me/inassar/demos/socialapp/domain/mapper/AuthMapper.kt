package me.inassar.demos.socialapp.domain.mapper

import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto
import me.inassar.demos.socialapp.data.remote.dto.signup.response.SignupResponseDto
import me.inassar.demos.socialapp.domain.model.login.LoginResponse
import me.inassar.demos.socialapp.domain.model.signup.SignupResponse

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    token = data?.token,
    username = data?.user?.username,
    errorMessage = error?.message
)

fun SignupResponseDto.toSignupResponse() = SignupResponse(
    token = data?.token,
    username = data?.user?.username,
    errorMessage = error?.message
)