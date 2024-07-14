package com.example.ekopay

import GeminiTextField
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ekopay.bottomnav.MainScreen
import com.example.ekopay.ui.theme.EKOPAYTheme
import com.google.ai.client.generativeai.BuildConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EKOPAYTheme {
                MainScreen()
            }
        }
    }
}


