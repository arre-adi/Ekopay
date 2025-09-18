@file:Suppress("UNUSED_EXPRESSION")

package com.example.ekopay.feature.qrscan.ui

import android.Manifest

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ekopay.feature.qrscan.presentation.QRScannerViewModel
import com.example.ekopay.ui.theme.Green1
import com.example.ekopay.ui.theme.Grey2
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.Executors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService

@Composable
fun QRScannerScreen(
    navController: NavController,
    viewModel: QRScannerViewModel = viewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val qrCode by viewModel.qrCode.collectAsState()
    var hasCameraPermission by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasCameraPermission = isGranted
        isLoading = false
    }

    LaunchedEffect(key1 = true) {
        launcher.launch(Manifest.permission.CAMERA)
    }

    LaunchedEffect(qrCode) {
        if (qrCode != null) {
            navController.navigate("success")
        }
    }

    DisposableEffect(lifecycleOwner) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasCameraPermission) {
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx).apply {
                        this.scaleType = PreviewView.ScaleType.FILL_CENTER
                    }

                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = Preview.Builder().build()
                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()

                        preview.setSurfaceProvider(previewView.surfaceProvider)

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()

                        imageAnalysis.setAnalyzer(cameraExecutor, QRCodeAnalyzer { result ->
                            coroutineScope.launch(Dispatchers.Main) {
                                viewModel.onQRCodeScanned(result)
                            }
                        })

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalysis
                            )
                        } catch (e: Exception) {
                            Log.e("QRScannerScreen", "Use case binding failed", e)
                        }

                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
            isLoading = false

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier
                            .background(
                                color = Color.Black.copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(
                            onClick = { /* Handle flash */ },
                            modifier = Modifier
                                .background(
                                    color = Color.Black.copy(alpha = 0.3f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Flash",
                                tint = Color.White
                            )
                        }

                    }
                }

                // Scanning frame
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(280.dp)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {

                    }
                }


                Button(
                    onClick = { /* Handle image picker */ },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 100.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.2f)
                    )
                ) {
                    Text("Upload from gallery", color = Color.White)
                }
            }
        }
        }

        if (!hasCameraPermission && !isLoading) {
            Text(
                "Camera permission is required to scan QR codes",

            )
        }

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp),
                    color = Color.White,
                    strokeWidth = 4.dp
                )
            }
        }
    }


class QRCodeAnalyzer(private val onQRCodeScanned: (String) -> Unit) : ImageAnalysis.Analyzer {
    private val scanner = BarcodeScanning.getClient()

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        barcode.rawValue?.let { qrCode ->
                            onQRCodeScanned(qrCode)
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("QRCodeAnalyzer", "QR Code scanning failed", e)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}




@Composable
fun SuccessScreen(navController: NavController) {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){

        Spacer(modifier = Modifier.padding(100.dp))
        Text(text = "Bamboo Life",
            color = Grey2,
            style = MaterialTheme.typography.headlineSmall)
        Text(text = "₹4000",
            fontSize = 50.sp,
            fontWeight = FontWeight(600))
        Text(text = "0.5GC",
            color = Green1,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight(600))
        Spacer(modifier = Modifier.padding(150.dp))
        Button(
            onClick = { navController.navigate("payment_done") }, // Navigate to a new screen
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Next")
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = false, showSystemUi = true)
@Composable
fun QRScannerScreenPreview() {
    val navController = rememberNavController()
    QRScannerScreen(navController = navController)
}
//
//@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SuccessScreenPreview() {
//    val navController = rememberNavController()
//    SuccessScreen(navController = navController)
//}
