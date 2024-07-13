package com.example.ekopay

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import kotlinx.coroutines.launch

class GeminiViewModel : ViewModel() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash-001",
        apiKey = "AIzaSyAXtm7Iau95V7mNeOKLG7yvMLo-Da5gsLo"
    )

    var searchQuery by mutableStateOf("")
    var answer by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun search() {
        viewModelScope.launch {
            isLoading = true
            try {
                val response: GenerateContentResponse = generativeModel.generateContent(searchQuery)
                answer = response.text ?: "No answer found"
            } catch (e: Exception) {
                answer = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}

@Composable
fun GeminiSearchBar(viewModel: GeminiViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.search() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(viewModel.answer)
        }
    }
}

// In your main composable function:
@Composable @Preview(showBackground = true, showSystemUi = true)
fun lullaScreen() {
    GeminiSearchBar()
}