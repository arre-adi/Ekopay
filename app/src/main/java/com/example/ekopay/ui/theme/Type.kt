package com.example.ekopay.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.ekopay.R
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1

val dmsansFontFamily = FontFamily(
    Font(R.font.dm_sans, FontWeight.Normal),
    Font(R.font.dm_sans_italic, FontWeight.ExtraLight)

)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = dmsansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = dmsansFontFamily,
        fontWeight = FontWeight.W800,
        fontSize = 2.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp,
        color = Green1
    )
)

