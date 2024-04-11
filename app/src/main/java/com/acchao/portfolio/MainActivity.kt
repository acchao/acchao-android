package com.acchao.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acchao.portfolio.ui.HomeScreen
import com.acchao.portfolio.ui.SplashScreen
import com.acchao.portfolio.ui.theme.PortfolioTheme
import com.acchao.portfolio.viewmodel.PortfolioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: PortfolioViewModel by viewModels()

        setContent {
            PortfolioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Portfolio()
                }
            }
        }
    }
}

@Composable
fun Portfolio(
    viewModel: PortfolioViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    if (viewModel.seenSplash.observeAsState().value == true) {
        HomeScreen()
    } else {
        SplashScreen()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 480, heightDp = 600)
@Composable
fun PortfolioPreview() {
    PortfolioTheme {
        Portfolio()
    }
}