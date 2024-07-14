package com.example.ekopay

import androidx.lifecycle.ViewModel
import com.google.ai.client.generativeai.GenerativeModel


class TextGeminiViewModel: ViewModel(){
    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.API_KEY
    )

}