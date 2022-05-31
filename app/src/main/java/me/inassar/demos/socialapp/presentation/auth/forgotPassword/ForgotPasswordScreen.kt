package me.inassar.demos.socialapp.presentation.auth.forgotPassword

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.inassar.demos.socialapp.R
import me.inassar.demos.socialapp.common.Routes

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        Shapes.Full.copy(
                            topEnd = CornerSize(0.dp),
                            topStart = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(90.dp)
                        )
                    )
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Image(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.bg_forgot_password_head),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Forgot password?",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val email = remember { mutableStateOf(TextFieldValue()) }

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Email") },
                    value = email.value,
                    onValueChange = { email.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = MaterialTheme.shapes.small,
                    singleLine = true,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                    onClick = {
                        Toast.makeText(context, "Hooray!!", Toast.LENGTH_SHORT).show()
                    }) {
                    Text(text = "Send OTP", color = MaterialTheme.colorScheme.onSecondaryContainer)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenForgotPasswordPrev() {
    ForgotPasswordScreen(rememberNavController())
}