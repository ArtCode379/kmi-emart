package kmiemart.consult.app.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmiemart.consult.app.R
import kmiemart.consult.app.ui.theme.GradientEnd
import kmiemart.consult.app.ui.theme.GradientStart
import kmiemart.consult.app.ui.viewmodel.KMSTMSplashVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: KMSTMSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboardedState by viewModel.onboardedState.collectAsStateWithLifecycle()
    var splashComplete by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1500L)
        splashComplete = true
    }

    LaunchedEffect(splashComplete, onboardedState) {
        if (splashComplete) {
            if (onboardedState) {
                onNavigateToHomeScreen()
            } else {
                onNavigateToOnboarding()
            }
        }
    }

    SplashScreenContent(modifier = modifier)
}

@Composable
fun SplashScreenContent(
    modifier: Modifier = Modifier,
) {
    val iconAlpha = remember { Animatable(0f) }
    val iconScale = remember { Animatable(0.8f) }
    val textAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        launch { iconAlpha.animateTo(1f, tween(800)) }
        launch { iconScale.animateTo(1f, tween(800)) }
        delay(200)
        textAlpha.animateTo(1f, tween(600))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(GradientStart, GradientEnd),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.mipmap.ic_launcher),
                contentDescription = "KMI E-Mart",
                modifier = Modifier
                    .size(120.dp)
                    .graphicsLayer {
                        alpha = iconAlpha.value
                        scaleX = iconScale.value
                        scaleY = iconScale.value
                    },
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "KMI E-Mart",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.graphicsLayer { alpha = textAlpha.value },
            )
        }
    }
}
