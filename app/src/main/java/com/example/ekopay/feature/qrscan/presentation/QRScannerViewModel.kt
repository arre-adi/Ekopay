package com.example.ekopay.feature.qrscan.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QRScannerViewModel : ViewModel() {

    private val _qrCode = MutableStateFlow<String?>(null)
    val qrCode = _qrCode.asStateFlow()

    fun onQRCodeScanned(code: String) {
        viewModelScope.launch {
            _qrCode.value = code
        }
    }

    fun resetQRCode() {
        _qrCode.value = null
    }
}