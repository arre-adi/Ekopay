package com.example.ekopay

import WelcomeBar
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun EcoBrickHomePage() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {


        WelcomeBar(
            userName = "Anon",
            userGreeting = "1234 5698 3699 4565",
            balanceText = "PLASTIC SUBMIT",
            balanceAmount = "500 KG"
        )
        StoreCheckoutPreview()
}
}



data class Step(
    val title: String,
    val content: @Composable () -> Unit
)

@Composable
fun StoreCheckout(
    steps: List<Step>,
    modifier: Modifier = Modifier
) {
    var currentStep by remember { mutableStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalStepProgressBar(
            steps = steps.map { it.title },
            currentStep = currentStep,
            modifier = Modifier.padding(16.dp)
        )

        Box(modifier = Modifier.weight(1f)) {
            steps[currentStep].content()
        }

        Button(
            onClick = {
                if (currentStep < steps.size - 1) {
                    currentStep++
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = currentStep < steps.size - 1
        ) {
            Text(if (currentStep == steps.size - 1) "Finish" else "Next")
        }
    }
}

@Composable
fun HorizontalStepProgressBar(
    steps: List<String>,
    currentStep: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        steps.forEachIndexed { index, step ->
            StepItem(
                stepNumber = index + 1,
                title = step,
                isCompleted = index <= currentStep,
                isActive = index == currentStep,
                isLastItem = index == steps.size - 1
            )
        }
    }
}

@Composable
fun StepItem(
    stepNumber: Int,
    title: String,
    isCompleted: Boolean,
    isActive: Boolean,
    isLastItem: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circle with number
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(
                        when {
                            isCompleted -> Color.Green
                            else -> Color.LightGray
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stepNumber.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            if (!isLastItem) {
                // Line connecting to next step
                Canvas(modifier = Modifier
                    .weight(1f)
                    .height(2.dp)) {
                    drawLine(
                        color = if (isCompleted) Color.Green else Color.LightGray,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
            color = if (isCompleted) Color.Black else Color.Gray
        )
    }
}

@Composable
fun CartScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Cart Screen - Review your items")
    }
}

@Composable
fun ShippingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Shipping Screen - Enter your address")
    }
}

@Composable
fun PaymentScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Payment Screen - Enter payment details")
    }
}

@Composable
fun ConfirmationScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Confirmation Screen - Order placed!")
    }
}

@Preview(showBackground = true)
@Composable
fun StoreCheckoutPreview() {
    val steps = listOf(
        Step("First") { CartScreen() },
        Step("Second") { ShippingScreen() },
        Step("Third") { PaymentScreen() },
        Step("Fourth") { ConfirmationScreen() }
    )

    StoreCheckout(steps = steps)
}