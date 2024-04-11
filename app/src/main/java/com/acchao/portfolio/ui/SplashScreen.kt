package com.acchao.portfolio.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acchao.portfolio.R
import com.acchao.portfolio.ui.theme.PortfolioTheme
import com.acchao.portfolio.viewmodel.PortfolioViewModel


@Composable
fun SplashScreen(
    viewModel: PortfolioViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

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
                    Row {
                        Text(
                            text = "Chao",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                        Text(
                            text = "...",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                        )
                    }
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
                            Text(
                                text = "senior software engineer",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "product engineer",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "entrepreneur",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(text = "home cook", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = "fitness enthusiast",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.padding(48.dp))
            Button(onClick = {
                viewModel.setHasSeenOnboarding()
            }) {
                Text("Latest Updates")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 480, heightDp = 600)
@Composable
fun SplashScreenPreview() {
    PortfolioTheme {
        SplashScreen()
    }
}