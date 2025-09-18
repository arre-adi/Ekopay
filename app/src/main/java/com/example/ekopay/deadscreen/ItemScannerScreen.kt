package com.example.ekopay.deadscreen

import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class EcoAlternative(
    val name: String,
    val description: String,
    val ecoScore: Int
)

data class ImageRecognitionRequest(
    val image: String  // Base64 encoded image
)

data class ImageRecognitionResponse(
    val itemName: String
)

interface ImageRecognitionApi {
    @POST("recognizeImage")
    suspend fun recognizeImage(@Body request: ImageRecognitionRequest): ImageRecognitionResponse
}

interface EcoAlternativeApi {
    @POST("getEcoAlternative")
    suspend fun getEcoFriendlyAlternative(@Body request: Map<String, String>): EcoAlternative
}

object ApiClient {
    private const val IMAGE_RECOGNITION_BASE_URL = "https://api.imagerecognition.com/"
    private const val ECO_ALTERNATIVE_BASE_URL = "https://api.ecoalternative.com/"

    val imageRecognitionApi: ImageRecognitionApi by lazy {
        Retrofit.Builder()
            .baseUrl(IMAGE_RECOGNITION_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageRecognitionApi::class.java)
    }

    val ecoAlternativeApi: EcoAlternativeApi by lazy {
        Retrofit.Builder()
            .baseUrl(ECO_ALTERNATIVE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EcoAlternativeApi::class.java)
    }
}

@Composable
fun ScanOrUpload(onImageCaptured: (Bitmap) -> Unit) {
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        bitmap?.let {
            imageBitmap = it
            onImageCaptured(it)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { launcher.launch() }) {
            Text("Take Picture")
        }

        imageBitmap?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun EcoFriendlyAlternative(itemName: String) {
    var ecoAlternative by remember { mutableStateOf<EcoAlternative?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(itemName) {
        scope.launch {
            try {
                ecoAlternative = ApiClient.ecoAlternativeApi.getEcoFriendlyAlternative(mapOf("itemName" to itemName))
            } catch (e: Exception) {
                Log.e("API Error", "Failed to fetch eco-friendly alternative", e)
            }
        }
    }

    ecoAlternative?.let {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Eco-friendly Alternative: ${it.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = it.description)
            Text(text = "Eco Score: ${it.ecoScore}")
        }
    } ?: run {
        CircularProgressIndicator()
    }
}

@Composable
fun JainScreen() {
    var selectedItem by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScanOrUpload { bitmap ->
            isLoading = true
            scope.launch {
                try {
                    // Convert bitmap to base64 string (implementation not shown)
                    val base64Image = bitmapToBase64(bitmap)
                    val response = ApiClient.imageRecognitionApi.recognizeImage(
                        ImageRecognitionRequest(base64Image)
                    )
                    selectedItem = response.itemName
                } catch (e: Exception) {
                    Log.e("API Error", "Failed to recognize image", e)
                } finally {
                    isLoading = false
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator()
        }

        selectedItem?.let {
            EcoFriendlyAlternative(it)
        }
    }
}

// Utility function to convert Bitmap to Base64 string
fun bitmapToBase64(bitmap: Bitmap): String {
    // Implementation goes here
    // This function should convert the bitmap to a Base64 encoded string
    return "base64EncodedString"
}