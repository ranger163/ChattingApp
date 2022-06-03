package me.inassar.demos.socialapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.signup.request.SignupRequestDto
import me.inassar.demos.socialapp.domain.mapper.toSignupResponse
import me.inassar.demos.socialapp.domain.model.signup.SignupResponse
import me.inassar.demos.socialapp.domain.repository.AuthRepository

class SignupUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(request: SignupRequestDto): Flow<ResponseResource<SignupResponse>> =
        flow {
            repository.signup(request).collect {
                when (it) {
                    is ResponseResource.Error -> emit(ResponseResource.error(it.errorMessage.toSignupResponse()))
                    is ResponseResource.Success -> emit(ResponseResource.success(it.data.toSignupResponse()))
                }
            }
        }
}