package me.inassar.demos.socialapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto
import me.inassar.demos.socialapp.data.remote.dto.signup.request.SignupRequestDto
import me.inassar.demos.socialapp.data.remote.dto.signup.response.SignupResponseDto

interface AuthRepository {
    suspend fun signup(requestDto: SignupRequestDto): Flow<ResponseResource<SignupResponseDto>>
    suspend fun login(request: LoginRequestDto): Flow<ResponseResource<LoginResponseDto>>
}