@file:JvmName("TextFieldStateKt")

package me.inassar.demos.socialapp.presentation.common

import android.util.Patterns
import java.util.regex.Pattern

class EmailState : TextFieldState(validator = ::isEmailValid, errorMessage = ::emailErrorMessage)
class PasswordState :
    TextFieldState(validator = ::isPasswordValid, errorMessage = ::passwordErrorMessage)

private fun isEmailValid(email: String): Boolean =
    Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), email)

private fun emailErrorMessage(email: String) =
    if (email.isEmpty()) "Email field is required." else "Email $email is invalid."

private fun isPasswordValid(password: String): Boolean =
    password.length >= 8

private fun passwordErrorMessage(password: String) =
    if (password.isEmpty()) "Email field is required." else "Password should be at least 8 characters."
