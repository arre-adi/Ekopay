@file:Suppress("UNUSED_EXPRESSION")

package com.example.ekopay.metroo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1
import com.example.ekopay.ui.theme.Grey2

@Composable
fun BengaluruMetroUI(navController: NavController) {
    var metroCardNumber by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val calculatedAmount = amount.toIntOrNull()?.times(.0002) ?: 0.0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bengaluru Metro",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = metroCardNumber,
            onValueChange = { metroCardNumber = it },
            label = { Text("Metro Card Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF1F1F1)),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF1F1F1)),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Text(
                    text = "$calculatedAmount GC",
                    color = Green1,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        )

        Button(
            onClick = {
                if (metroCardNumber.isNotEmpty() && amount.isNotEmpty()) {
                    navController.navigate("metro_price/$metroCardNumber/$amount")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Black1,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Next")
        }
    }
}


@Composable
fun MetroPriceScreen(navController: NavController, metroCardNumber: String, amount: String) {
    val calculatedAmount = amount.toDoubleOrNull()?.times(0.0002) ?: 0.0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.padding(100.dp))
        Text(
            text = "Bengaluru Metro",
            color = Grey2,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Card Number: $metroCardNumber",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Payment",
            fontSize = 50.sp,
            fontWeight = FontWeight(600)
        )
        Text(
            text = "Complete",
            fontSize = 50.sp,
            fontWeight = FontWeight(600)
        )
        Text(
            text = "$calculatedAmount GC earned",
            color = Green1,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight(600)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BengaluruMetroUIPreview() {
    val navController = rememberNavController()
    BengaluruMetroUI(navController)
}