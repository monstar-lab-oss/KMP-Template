package presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.feature.splash.SplashScreen
import presentation.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        Navigator(SplashScreen) { SlideTransition(it) }
    }
}
