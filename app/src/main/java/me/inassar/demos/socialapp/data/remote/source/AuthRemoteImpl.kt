package me.inassar.demos.socialapp.data.remote.source

import io.ktor.client.*
import io.ktor.client.request.*
import me.inassar.demos.socialapp.common.ENDPOINT_LOGIN
import me.inassar.demos.socialapp.common.ENDPOINT_SIGNUP
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.data.remote.dto.login.request.LoginRequestDto
import me.inassar.demos.socialapp.data.remote.dto.login.response.LoginResponseDto
import me.inassar.demos.socialapp.data.remote.dto.signup.request.SignupRequestDto
import me.inassar.demos.socialapp.data.remote.dto.signup.response.SignupResponseDto

class AuthRemoteImpl(private val client: HttpClient) : AuthRemote {

    override suspend fun signup(request: SignupRequestDto): ResponseResource<SignupResponseDto> =
        try {
            val response = client.post<SignupResponseDto>(ENDPOINT_SIGNUP) {
                body = request
            }
            when (response.data) {
                null -> ResponseResource.error(response)
                else -> ResponseResource.success(response)
            }
        } catch (e: Exception) {
            ResponseResource.error(SignupResponseDto(error = SignupResponseDto.Error("Oops, something bad happened :(")))
        }

    override suspend fun login(request: LoginRequestDto): ResponseResource<LoginResponseDto> = try {
        val response = client.post<LoginResponseDto>(ENDPOINT_LOGIN) {
            body = request
        }
        when (response.data) {
            null -> ResponseResource.error(response)
            else -> ResponseResource.success(response)
        }
    } catch (e: Exception) {
        ResponseResource.error(LoginResponseDto(error = LoginResponseDto.Error("Oops, something bad happened :(")))
    }
}