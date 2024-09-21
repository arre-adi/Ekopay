package com.example.ekopay

import GeminiTextField
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.ekopay.bottomnav.MainScreen
import com.example.ekopay.ui.theme.EKOPAYTheme
import android.Manifest
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.ai.client.generativeai.BuildConfig

class MainActivity : ComponentActivity() {

    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            setContent {
                EKOPAYTheme {
                    MainScreen()
                }
            }
        } else {
            // Permission denied, close the app
            Toast.makeText(this, "Camera permission is required. Closing app.", Toast.LENGTH_LONG).show()
            finish() // Close the app if permission is denied
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Check if the camera permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
            // If permission is already granted, proceed
            setContent {
                EKOPAYTheme {
                    MainScreen()
                }
            }
        } else {
            // Request permission
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

