package com.example.ekopay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1
import com.example.ekopay.ui.theme.Grey2
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FundingPreview(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "CROWD FUNDING",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        FundingList(navController)

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .background(
                    Black1,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.frame_3),
                contentDescription = "Green Credit Card Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Support Startups and NGO on ")
                        withStyle(style = SpanStyle(color = Green1)) {
                            append("EKOPAY")
                        }
                        append(" \nand earn ")
                        withStyle(style = SpanStyle(color = Green1)) {
                            append("GREEN CREDITS")
                        }
                    },
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )
            }
        }
    }
}

@Composable
fun FundingList(navController: NavController) {
    val allFundings = listOf(
        Funding("Nayi Kaya",  "NGO", 2000, 236522),
        Funding("Bamboo Lab",  "Startup", 8530, 31651),
        Funding("Sankalp",  "NGO", 45600, 41919),
        Funding("Mitti Built", "Startup", 4050, 54649),
        Funding("Navjeevvan",  "NGO", 45090, 62629)
    )

    LazyColumn {
        items(allFundings) { funding ->
            FundingItem(funding, navController)
        }
    }
}

@Composable
fun FundingItem(funding: Funding, navController: NavController) {
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("add_money/${funding.name}")
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (funding.type == "NGO") Green1 else Black1,
                    RoundedCornerShape(8.dp)
                )
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(funding.name, fontWeight = FontWeight.Bold)
            Text(funding.type, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                numberFormat.format(funding.money),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(" out of ₹${numberFormat.format(funding.greenCredits)}", fontSize = 12.sp, color = Green1)
        }
    }
}

data class Funding(
    val name: String,
    val type: String,
    val money: Int,
    val greenCredits: Int
)

@Composable
fun AddMoney(navController: NavController, name: String) {
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
            text = name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(600),
            modifier = Modifier.padding(bottom = 32.dp)
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
                navController.navigate("crowdfunding_payment/$name/${amount}")
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
fun CrowdFundingPaymentScreen(navController: NavController, name: String, amount: String) {
    val calculatedAmount = amount.toDoubleOrNull()?.times(0.0002) ?: 0.0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "thanks for the support ✨",
            color = Grey2,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = name,
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
fun FundingPreviewPreview() {
    val navController = rememberNavController()
    FundingPreview(navController)
}