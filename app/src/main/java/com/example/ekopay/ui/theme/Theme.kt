package com.example.ekopay.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White




val TextPrimary = Color(0xFF000000)
val TextSecondary = Color(0xFF666666)

private val LightColorScheme =   lightColorScheme(
    primary = White,
    onPrimary = Black,
    secondary = White,
    onSecondary = Black,
    background = White,
    surface = White,
)

@Composable//
fun EKOPAYTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}