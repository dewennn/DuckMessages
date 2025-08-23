package com.application.DuckMessages.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Define your custom colors here
val DarkYellow = Color(0xFFF9B32E)
val LightYellow = Color(0xFFFDF4A6)
val White = Color(0xFFFAFAFA)
val Black = Color(0xFF121212)
val Blue = Color(0xFF4A90E2)

// These are the color palettes your app will use
val DarkColorScheme = darkColorScheme(
    primary = Black,
    secondary = White,
    onPrimary = DarkYellow,
    onSecondary = LightYellow,
    tertiary = Blue
)

val LightColorScheme = lightColorScheme(
    primary = White,
    secondary = Black,
    onPrimary = LightYellow,
    onSecondary = DarkYellow,
    tertiary = Blue
)