package com.example.ekopay.feature.payments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekopay.ui.theme.Green1


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun paymentcompletescreen() {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){

        Spacer(modifier = Modifier.padding(100.dp))
        Text(text = "PAYMENT",
            fontSize = 30.sp,
            fontWeight = FontWeight(600)
        )
        Text(text = "COMPLETE",
            fontSize = 30.sp,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Text(text = "0.5GC earned",
            color = Green1,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight(600)
        )
        Spacer(modifier = Modifier.padding(150.dp))

    }
}

