package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import presentation.theme.dimensions.Dimensions
import presentation.theme.dimensions.LocalDimensions
import presentation.theme.dimensions.ProvideDimensions
import presentation.theme.typography.AppTypography
import presentation.theme.typography.LocalAppTypography
import presentation.theme.typography.ProvideAppTypography
import presentation.theme.typography.Typography

/**
 * Main theme provider
 * Use [Theme.*] to access colors, typography and etc
 */
@Composable
fun AppTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val dimensions = Dimensions.Default
    ProvideDimensions(dimensions = dimensions) {
        ProvideAppTypography {
            MaterialTheme(
                typography = Typography,
                shapes = Shapes,
                content = content,
                colors = if (isDarkMode) DarkColors else LightColors,
            )
        }
    }
}

/**
 * Shortcut to obtain App Theme values instead of using [MaterialTheme]
 * - Provides custom AppTypography instead of Material one
 * - Provides access to dimensions, that can vary based on device size
 */
object Theme {
    val typography: AppTypography @Composable get() = LocalAppTypography.current
    val colors: Colors @Composable get() = MaterialTheme.colors
    val shapes: Shapes @Composable get() = MaterialTheme.shapes
    val dimensions: Dimensions @Composable get() = LocalDimensions.current
}

private val LightColors = lightColors(
    primary = MonstarlabYellow,
    onPrimary = Black,
    background = LightGrey,
    onBackground = DarkGrey,
    onSurface = Black,
    surface = White,
    error = Red,
)

private val DarkColors = darkColors(
    primary = MonstarlabYellow,
    onPrimary = Black,
    background = DarkGrey,
    onBackground = White,
    onSurface = White,
    surface = Black,
    error = Red,
)
