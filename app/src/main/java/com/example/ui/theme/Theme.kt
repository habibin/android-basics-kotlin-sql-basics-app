package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.data.R

private val LightColors = lightColorScheme(
    primary = Color(R.color.md_theme_light_primary),
    onPrimary = Color(R.color.md_theme_light_onPrimary),
    primaryContainer = Color(R.color.md_theme_light_primaryContainer),
    onPrimaryContainer = Color(R.color.md_theme_light_onPrimaryContainer),
    secondary = Color(R.color.md_theme_light_secondary),
    onSecondary = Color(R.color.md_theme_light_onSecondary),
    secondaryContainer = Color(R.color.md_theme_light_secondaryContainer),
    onSecondaryContainer = Color(R.color.md_theme_light_onSecondaryContainer),
    tertiary = Color(R.color.md_theme_light_tertiary),
    onTertiary = Color(R.color.md_theme_light_onTertiary),
    tertiaryContainer = Color(R.color.md_theme_light_tertiaryContainer),
    onTertiaryContainer = Color(R.color.md_theme_light_onTertiaryContainer),
    error = Color(R.color.md_theme_light_error),
    errorContainer = Color(R.color.md_theme_light_errorContainer),
    onError = Color(R.color.md_theme_light_onError),
    onErrorContainer = Color(R.color.md_theme_light_onErrorContainer),
    background = Color(R.color.md_theme_light_background),
    onBackground = Color(R.color.md_theme_light_onBackground),
    surface = Color(R.color.md_theme_light_surface),
    onSurface = Color(R.color.md_theme_light_onSurface),
    surfaceVariant = Color(R.color.md_theme_light_surfaceVariant),
    onSurfaceVariant = Color(R.color.md_theme_light_onSurfaceVariant),
    outline = Color(R.color.md_theme_light_outline),
    inverseOnSurface = Color(R.color.md_theme_light_inverseOnSurface),
    inverseSurface = Color(R.color.md_theme_light_inverseSurface),
    inversePrimary = Color(R.color.md_theme_light_inversePrimary),
    surfaceTint = Color(R.color.md_theme_light_surfaceTint),
    outlineVariant = Color(R.color.md_theme_light_outlineVariant),
    scrim = Color(R.color.md_theme_light_scrim)
)

private val DarkColors = darkColorScheme(
    primary = Color(R.color.md_theme_dark_primary),
    onPrimary = Color(R.color.md_theme_dark_onPrimary),
    primaryContainer = Color(R.color.md_theme_dark_primaryContainer),
    onPrimaryContainer = Color(R.color.md_theme_dark_onPrimaryContainer),
    secondary = Color(R.color.md_theme_dark_secondary),
    onSecondary = Color(R.color.md_theme_dark_onSecondary),
    secondaryContainer = Color(R.color.md_theme_dark_secondaryContainer),
    onSecondaryContainer = Color(R.color.md_theme_dark_onSecondaryContainer),
    tertiary = Color(R.color.md_theme_dark_tertiary),
    onTertiary = Color(R.color.md_theme_dark_onTertiary),
    tertiaryContainer = Color(R.color.md_theme_dark_tertiaryContainer),
    onTertiaryContainer = Color(R.color.md_theme_dark_onTertiaryContainer),
    error = Color(R.color.md_theme_dark_error),
    errorContainer = Color(R.color.md_theme_dark_errorContainer),
    onError = Color(R.color.md_theme_dark_onError),
    onErrorContainer = Color(R.color.md_theme_dark_onErrorContainer),
    background = Color(R.color.md_theme_dark_background),
    onBackground = Color(R.color.md_theme_dark_onBackground),
    surface = Color(R.color.md_theme_dark_surface),
    onSurface = Color(R.color.md_theme_dark_onSurface),
    surfaceVariant = Color(R.color.md_theme_dark_surfaceVariant),
    onSurfaceVariant = Color(R.color.md_theme_dark_onSurfaceVariant),
    outline = Color(R.color.md_theme_dark_outline),
    inverseOnSurface = Color(R.color.md_theme_dark_inverseOnSurface),
    inverseSurface = Color(R.color.md_theme_dark_inverseSurface),
    inversePrimary = Color(R.color.md_theme_dark_inversePrimary),
    surfaceTint = Color(R.color.md_theme_dark_surfaceTint),
    outlineVariant = Color(R.color.md_theme_dark_outlineVariant),
    scrim = Color(R.color.md_theme_dark_scrim)
)
@Composable
fun CupcakeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography, // Use the correct Typography from the material3 package
        content = content
    )
}