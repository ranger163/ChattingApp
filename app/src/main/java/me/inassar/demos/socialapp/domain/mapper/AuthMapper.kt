package me.inassar.demos.socialapp.domain.mapper

import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto
import me.inassar.demos.socialapp.data.remote.dto.signup.response.SignupResponseDto
import me.inassar.demos.socialapp.domain.model.login.LoginResponse
import me.inassar.demos.socialapp.domain.model.signup.SignupResponse
import me.inassar.demos.socialapp.domain.model.user.User

fun LoginResponseDto.toLoginResponse() = LoginResponse(
    token = data?.token,
    username = data?.user?.username,
    email = data?.user?.email,
    avatar = data?.user?.avatar,
    errorMessage = error?.message
)

fun SignupResponseDto.toSignupResponse() = SignupResponse(
    token = data?.token,
    username = data?.user?.username,
    email = data?.user?.email,
    avatar = data?.user?.avatar,
    errorMessage = error?.message
)

fun LoginResponse.toUser() = User(
    username = username,
    email = email,
    token = token,
    avatar = avatar
)

fun SignupResponse.toUser() = User(
    username = username,
    email = email,
    token = token,
    avatar = avatar
)