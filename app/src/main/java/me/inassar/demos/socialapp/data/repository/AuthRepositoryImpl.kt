package me.inassar.demos.socialapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto
import me.inassar.demos.socialapp.data.remote.dto.signup.request.SignupRequestDto
import me.inassar.demos.socialapp.data.remote.dto.signup.response.SignupResponseDto
import me.inassar.demos.socialapp.data.remote.source.AuthRemote
import me.inassar.demos.socialapp.domain.repository.AuthRepository

class AuthRepositoryImpl(private val remote: AuthRemote) : AuthRepository {

    override suspend fun signup(requestDto: SignupRequestDto): Flow<ResponseResource<SignupResponseDto>> =
        flow {
            when (val response = remote.signup(requestDto)) {
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }

    override suspend fun login(request: LoginRequestDto): Flow<ResponseResource<LoginResponseDto>> =
        flow {
            when (val response = remote.login(request)) {
                is ResponseResource.Error -> emit(ResponseResource.error(response.errorMessage))
                is ResponseResource.Success -> emit(ResponseResource.success(response.data))
            }
        }

}