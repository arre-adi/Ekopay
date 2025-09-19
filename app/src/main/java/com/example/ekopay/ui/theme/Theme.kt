package com.example.ekopay.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



val TextPrimary = Color(0xFF000000)
val TextSecondary = Color(0xFF666666)

private val FixedColorScheme = lightColorScheme(
    primary = Green1,
    secondary = White1,
    tertiary =Black1,
    background = White1,
    surface = White1,
    surfaceVariant = Black1
)

@Composable
fun EkopayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled to maintain consistent colors
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = FixedColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
