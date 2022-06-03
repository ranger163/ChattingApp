package me.inassar.demos.socialapp.presentation.auth.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.inassar.demos.socialapp.common.ResponseResource
import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.data.remote.dto.signup.request.SignupRequestDto
import me.inassar.demos.socialapp.domain.mapper.toUser
import me.inassar.demos.socialapp.domain.usecase.SignupUseCase
import me.inassar.demos.socialapp.presentation.common.EmailState
import me.inassar.demos.socialapp.presentation.common.PasswordState
import me.inassar.demos.socialapp.presentation.common.UsernameState

class SignupViewModel(
    private val useCase: SignupUseCase,
    private val sessionPrefs: SessionPrefs
) : ViewModel() {

    private val _usernameState = UsernameState()
    val usernameState = _usernameState

    private val _emailState = EmailState()
    val emailState: EmailState = _emailState

    private val _passwordState = PasswordState()
    val passwordState: PasswordState = _passwordState

    private val _confirmPasswordState = PasswordState()
    val confirmPasswordState: PasswordState = _confirmPasswordState

    private val _signupState = mutableStateOf(SignupState())
    val signupState: State<SignupState> = _signupState

    fun onUsernameChange(username: String) {
        _usernameState.text = username
    }

    fun onEmailChange(email: String) {
        _emailState.text = email
    }

    fun onPasswordChange(password: String) {
        _passwordState.text = password
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _confirmPasswordState.text = confirmPassword
    }

    fun performSignup() {
        if (validateInputs())
            viewModelScope.launch {
                _signupState.value = SignupState(isLoading = true)
                val request = SignupRequestDto(
                    username = usernameState.text,
                    email = emailState.text,
                    password = passwordState.text
                )

                useCase(request).collect {
                    when (it) {
                        is ResponseResource.Error -> _signupState.value =
                            SignupState(error = it.errorMessage.errorMessage.orEmpty())
                        is ResponseResource.Success -> {
                            sessionPrefs.saveUser(it.data.toUser().copy(isLoggedIn = true))
                            _signupState.value =
                                SignupState(data = it.data)
                        }
                    }
                }
            }
    }

    private fun validateInputs(): Boolean {
        if (usernameState.text.isEmpty()) {
            _usernameState.validate()
            return false
        }
        if (emailState.text.isEmpty()) {
            _emailState.validate()
            return false
        }
        if (passwordState.text.isEmpty()) {
            _passwordState.validate()
            return false
        }
        if (confirmPasswordState.text.isEmpty()) {
            _confirmPasswordState.validate()
            return false
        }
        if (passwordState.text != confirmPasswordState.text) {
            _confirmPasswordState.error = "Password mismatched!"
            return false
        }
        return true
    }

}