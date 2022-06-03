package me.inassar.demos.socialapp.presentation.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.presentation.common.Routes
import me.inassar.demos.socialapp.common.navigateTo
import org.koin.androidx.compose.inject

@Composable
fun SplashScreen(navController: NavController) {
    val sessionPrefs by inject<SessionPrefs>()

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = { OvershootInterpolator(4f).getInterpolation(it) })
        )
        delay(800L)
        navigateTo(
            navController = navController,
            destination = if (sessionPrefs.getUser()?.isLoggedIn == true) Routes.FriendsList.route else Routes.Login.route,
            clearBackStack = true
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.scale(scale.value),
            text = "iNassar Demos",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily.Monospace)
        )
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun ScreenSplashPrev() {
    SplashScreen(rememberNavController())
}