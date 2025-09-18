package com.example.ekopay.feature.ai.presentation

import androidx.lifecycle.ViewModel
import com.example.ekopay.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel

class TextGeminiViewModel: ViewModel(){
    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.API_KEY
    )

}