package com.acchao.portfolio.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.acchao.portfolio.navigation.PortfolioNavHost
import com.acchao.portfolio.ui.theme.PortfolioTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note(Andrew): On android 12, launching from android studio causes a blank
        // splash screen to load. Please click the launcher icon
        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }

        setContent {
            PortfolioApp(Modifier.fillMaxSize(), splashViewModel)
        }
    }
}

@Composable
fun PortfolioApp(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel
) {
    PortfolioTheme {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            val startDestination by splashViewModel.startDestination
            val navController = rememberNavController()
            PortfolioNavHost(navController = navController, startDestination = startDestination)
        }
    }
}