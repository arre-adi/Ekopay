package com.example.ekopay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1


@Composable @Preview(showBackground = true, showSystemUi = true)
fun ProductCardfinal() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Column {
            // Image Section
            Image(
                painter = painterResource(id = R.drawable.imgapple), // Placeholder for your image
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Product Title and Subtitle
            Text(
                text = "Organic Apples",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )

            Text(
                text = "Homegrown",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Price and Green Credits
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "₹200",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )

                Text(
                    text = "0.5GC",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 26.sp,
                        color = Green1,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Product Description
            Text(
                text = "These apples used 50% less water while growing and 90% less pesticides",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    color = Color.Gray,
                )
            )
            Spacer(modifier = Modifier
                .height(150.dp))
            Button(
                onClick = { /*TODO*/
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black1,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("ADD TO CART")
            }
        }
    }
}

