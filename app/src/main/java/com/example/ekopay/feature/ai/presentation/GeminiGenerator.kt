import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ekopay.BuildConfig
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.White1
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.launch

@Composable@Preview(showSystemUi = true, showBackground = true)
fun GeminiTextField() {
    var text by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel: TextGeminiViewModel = viewModel()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            leadingIcon = {
            IconButton(onClick = {/*TODO*/}){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        },
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your text") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF1F1F1)),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    viewModel.generateText(text) { generatedText ->
                        result = generatedText
                    }
                }
            ),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (result.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .background(
                        White1,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(result, color = Black1)
            }
        }
    }
}

class TextGeminiViewModel : ViewModel() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.API_KEY
    )

    fun generateText(prompt: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = generativeModel.generateContent(prompt)
                onResult(response.text ?: "No response generated")
            } catch (e: Exception) {
                onResult("Error: ${e.message}")
            }
        }
    }
}