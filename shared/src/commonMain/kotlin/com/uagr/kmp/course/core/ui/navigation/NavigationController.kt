package com.uagr.kmp.course.core.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import com.uagr.kmp.course.core.ui.theme.AppTheme
import com.uagr.kmp.course.feature.home.presentation.HomeContainer
import com.uagr.kmp.course.feature.welcome.presentation.navigation.WelcomeContainer

@Composable
@Preview(showSystemUi = true)
fun NavigationController() {
    AppTheme {
        Navigator(screen = WelcomeScreen()) { navigator ->
            AnimatedVisibility(visible = true) { // Animación de transición entre pantallas
                SlideTransition(navigator)
                //FadeTransition(navigator)
                //ScaleTransition(navigator)
            }
        }
    }
}

class WelcomeScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        WelcomeContainer {
            // Navegar hacia delanta
            navigator.push(HomeScreen(1))
        }
    }
}


data class HomeScreen(val postId: Long): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeContainer {
            // Regresar hacia atrás
            navigator.pop()
        }
    }
}

