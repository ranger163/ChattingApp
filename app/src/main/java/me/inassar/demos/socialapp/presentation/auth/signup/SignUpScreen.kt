package me.inassar.demos.socialapp.presentation.auth.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.inassar.demos.socialapp.R
import me.inassar.demos.socialapp.common.navigateTo
import me.inassar.demos.socialapp.presentation.common.*
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignupViewModel = getViewModel()) {
    val signupState = viewModel.signupState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        if (signupState.data != null)
            LaunchedEffect(key1 = true) {
                navigateTo(
                    navController = navController,
                    destination = Routes.FriendsList.route,
                    clearBackStack = true
                )
            }

        Column(modifier = Modifier.fillMaxSize()) {

            AuthHeader(
                title = "Create account,",
                painterResource = R.drawable.bg_sing_up_head
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val passwordVisible = remember { mutableStateOf(false) }
                val confirmPasswordVisible = remember { mutableStateOf(false) }

                TextFieldWithError(
                    label = "Username",
                    value = viewModel.usernameState.text,
                    keyboardType = KeyboardType.Text,
                    isError = viewModel.usernameState.error != null,
                    errorMessage = viewModel.usernameState.error,
                    onValueChange = {
                        viewModel.onUsernameChange(it)
                        viewModel.usernameState.validate()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithError(
                    label = "Email",
                    value = viewModel.emailState.text,
                    keyboardType = KeyboardType.Email,
                    isError = viewModel.emailState.error != null,
                    errorMessage = viewModel.emailState.error,
                    onValueChange = {
                        viewModel.onEmailChange(it)
                        viewModel.emailState.validate()
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithError(
                    label = "Password",
                    value = viewModel.passwordState.text,
                    keyboardType = KeyboardType.Password,
                    isError = viewModel.passwordState.error != null,
                    errorMessage = viewModel.passwordState.error,
                    onValueChange = {
                        viewModel.onPasswordChange(it)
                        viewModel.passwordState.validate()
                    },
                    trailingIcon = {
                        val eyeIcon = if (passwordVisible.value) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val contentDescription =
                            if (passwordVisible.value) "Hide password" else "Show password"
                        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                            Icon(imageVector = eyeIcon, contentDescription = contentDescription)
                        }
                    },
                    visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(20.dp))

                TextFieldWithError(
                    label = "Confirm Password",
                    value = viewModel.confirmPasswordState.text,
                    keyboardType = KeyboardType.Password,
                    isError = viewModel.confirmPasswordState.error != null,
                    errorMessage = viewModel.confirmPasswordState.error,
                    onValueChange = {
                        viewModel.onConfirmPasswordChange(it)
                        viewModel.confirmPasswordState.validate()
                    },
                    trailingIcon = {
                        val eyeIcon = if (confirmPasswordVisible.value) Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val contentDescription =
                            if (confirmPasswordVisible.value) "Hide password" else "Show password"
                        IconButton(onClick = {
                            confirmPasswordVisible.value = !confirmPasswordVisible.value
                        }) {
                            Icon(imageVector = eyeIcon, contentDescription = contentDescription)
                        }
                    },
                    visualTransformation = if (confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (signupState.error.isNotBlank())
                    ErrorText(signupState.error)

                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    onClick = {
                        viewModel.performSignup()
                    }) {
                    Text(text = "Sign up", color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }

        val annotatedText = buildAnnotatedString {
            //append your initial text
            withStyle(style = SpanStyle(color = Color.Gray)) { append("Already have account? ") }

            //Start of the pushing annotation which you want to color and make them clickable later
            pushStringAnnotation(
                tag = "Login", // provide tag which will then be provided when you click the text
                annotation = "Login"
            )

            //add text with your different color/style
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append("Login")
            }

            withStyle(style = SpanStyle(color = Color.Gray)) { append(".") }

            // when pop is called it means the end of annotation with current tag
            pop()
        }
        ClickableText(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(20.dp),
            text = annotatedText,
            onClick = { offset ->
                annotatedText.getStringAnnotations(
                    tag = "Login",// tag which you used in the buildAnnotatedString
                    start = offset, end = offset
                ).firstOrNull()?.let {
                    navController.popBackStack()
                }
            })
    }

    Loader(isLoading = signupState.isLoading)
}

@Preview(showBackground = true)
@Composable
fun ScreenSignupPrev() {
    SignUpScreen(rememberNavController())
}