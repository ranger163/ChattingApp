package me.inassar.demos.socialapp.data.remote.source

import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto

interface AuthRemote {
    suspend fun login(request: LoginRequestDto): ResponseResource<LoginResponseDto>
}