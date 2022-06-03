package me.inassar.demos.socialapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto

interface AuthRepository {
    suspend fun login(request: LoginRequestDto): Flow<ResponseResource<LoginResponseDto>>
}