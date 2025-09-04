package com.seekho.harichandanaghanta.animeverse.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val BlackAndWhiteColorScheme = lightColorScheme(
    primary = BwPrimary,
    onPrimary = BwOnPrimary,
    primaryContainer = BwPrimaryContainer,
    onPrimaryContainer = BwOnPrimaryContainer,
    secondary = BwSecondary,
    onSecondary = BwOnSecondary,
    secondaryContainer = BwSecondaryContainer,
    onSecondaryContainer = BwOnSecondaryContainer,
    tertiary = BwTertiary,
    onTertiary = BwOnTertiary,
    tertiaryContainer = BwTertiaryContainer,
    onTertiaryContainer = BwOnTertiaryContainer,
    error = BwError,
    onError = BwOnError,
    errorContainer = BwErrorContainer,
    onErrorContainer = BwOnErrorContainer,
    background = BwBackground,
    onBackground = BwOnBackground,
    surface = BwSurface,
    onSurface = BwOnSurface,
    surfaceVariant = BwSurfaceVariant,
    onSurfaceVariant = BwOnSurfaceVariant,
    outline = BwOutline,
    outlineVariant = BwOutlineVariant,
    scrim = BwScrim,
    inverseSurface = BwInverseSurface,
    inverseOnSurface = BwInverseOnSurface,
    inversePrimary = BwInversePrimary
)

@Composable
fun AnimeVerseTheme(
    // For this simple black and white theme, we don't support dark theme switching
    // darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled for a strict black and white theme
    // dynamicColor: Boolean = false, 
    content: @Composable () -> Unit
) {
    val colorScheme = BlackAndWhiteColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false // Assuming dark status bar icons on light primary
            // For a pure white status bar, you might want light icons (true)
            // and for a pure black status bar, dark icons (false)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
