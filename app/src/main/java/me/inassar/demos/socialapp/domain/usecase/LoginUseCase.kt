package me.inassar.demos.socialapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import me.inassar.demos.socialapp.domain.mapper.toLoginResponse
import me.inassar.demos.socialapp.domain.model.login.LoginResponse
import me.inassar.demos.socialapp.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(requestDto: LoginRequestDto): Flow<ResponseResource<LoginResponse>> =
        flow {
            repository.login(requestDto).collect {
                when (it) {
                    is ResponseResource.Error -> emit(ResponseResource.error(it.errorMessage.toLoginResponse()))
                    is ResponseResource.Success -> emit(ResponseResource.success(it.data.toLoginResponse()))
                }
            }
        }
}