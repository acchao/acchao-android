package com.acchao.portfolio.ui.welcome

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acchao.portfolio.R
import com.acchao.portfolio.ui.theme.PortfolioTheme
import com.acchao.portfolio.ui.util.AnimatedInvisibility
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    onContinueClicked: () -> Unit,
) {
    val animationOrder = remember { mutableIntStateOf(0) }

    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth()
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.andrew),
                contentDescription = "selfie",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Row(
            ) {
                Column {
                    Text(
                        text = "Andrew",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                    TextWithLoadingDots(
                        "Chao",
                        isAnimating = animationOrder.value < 5,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Row(
                        modifier = Modifier.padding(top = 24.dp)
                    ) {
                        Column {
                            Text(
                                text = "I am a ",
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.Start,
                        ) {

                            LaunchedEffect(key1 = Unit) {
                                coroutineScope {
                                    launch {
                                        repeat(6) {
                                            delay(1600)
                                            animationOrder.intValue++
                                        }
                                    }
                                }
                            }

                            AnimatedInvisibility(visible = animationOrder.intValue >= 1) {
                                Text(
                                    text = "senior software engineer",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = it
                                )
                            }
                            AnimatedInvisibility(visible = animationOrder.intValue >= 2) {
                                Text(
                                    text = "product engineer",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = it
                                )
                            }
                            AnimatedInvisibility(visible = animationOrder.intValue >= 3) {
                                Text(
                                    text = "entrepreneur",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = it
                                )
                            }
                            AnimatedInvisibility(visible = animationOrder.intValue >= 4) {
                                Text(
                                    text = "home cook",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = it
                                )
                            }
                            AnimatedInvisibility(visible = animationOrder.intValue >= 5) {
                                Text(
                                    text = "fitness enthusiast",
                                    style = MaterialTheme.typography.titleMedium,
                                    modifier = it
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.padding(48.dp))
            val buttonIsVisible = animationOrder.intValue >= 6
            val animatedAlpha by animateFloatAsState(
                targetValue = if (buttonIsVisible) 1.0f else 0f,
                label = "alpha"
            )
            Button(
                onClick = {
                    welcomeViewModel.setHasSeenSplash(true)
                    onContinueClicked()
                },
                enabled = buttonIsVisible,
                modifier = Modifier.graphicsLayer { alpha = animatedAlpha }
            ) {
                Text("Latest Updates")
            }
        }
    }
}

@Composable
fun TextWithLoadingDots(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    isAnimating: Boolean = true,
) {
    Row(modifier) {
        Text(
            text = text,
            style = style,
        )

        val duration = 1500
        val transition = rememberInfiniteTransition("Dots Transition")

        // Define the animated value for the number of visible dots
        val visibleDotsCount = transition.animateValue(
            initialValue = 0,
            targetValue = 4,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = duration,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ),
            label = "Visible Dots Count"
        )

        val animatedText = if (isAnimating) {
            "" + ".".repeat(visibleDotsCount.value)
        } else {
            ""
        }

        Text(
            text = animatedText,
            style = style,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true, widthDp = 480, heightDp = 600)
@Composable
fun SplashScreenPreview() {
    PortfolioTheme {
        WelcomeScreen {
            // do nothing
        }
    }
}