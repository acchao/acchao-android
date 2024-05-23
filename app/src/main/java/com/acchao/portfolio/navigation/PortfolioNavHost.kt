package com.acchao.portfolio.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.acchao.portfolio.ui.home.HomeScreen
import com.acchao.portfolio.ui.welcome.WelcomeScreen

interface PortfolioDestination {
    val route: String
}

object Splash : PortfolioDestination {
    override val route: String = "welcome"
}

object Home : PortfolioDestination {
    override val route: String = "home"
}

@Composable
fun PortfolioNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
        ) {
        composable(route = Splash.route)  {
            WelcomeScreen(
                onContinueClicked = {
                    navController.navigateSingleTopTo(Home.route)
                }
            )
        }
        composable(route = Home.route) {
            HomeScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }