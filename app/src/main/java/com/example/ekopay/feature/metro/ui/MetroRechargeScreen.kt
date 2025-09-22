package com.example.ekopay.feature.metro.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ekopay.R
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1

data class MetroData(
    val name: String,
    val logoRes: Int,
    val route: String
)

@Composable
fun SelectMetroScreen(navController: NavController) {
    val metros = remember {
        listOf(
            MetroData("Bengaluru Metro", R.drawable.img_metro_bengaluru,"b_metro"),
            MetroData("Chennai Metro", R.drawable.img_metro_chennai, "chennai_metro"),
            MetroData("Delhi Metro", R.drawable.img_metro_delhi,  "delhi_metro"),
            MetroData("Kochi Metro", R.drawable.img_metro_kochi,  "kochi_metro")
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Recharge Metro Card",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            items(metros) { metro ->
                MetroItem(
                    metroData = metro,
                    onClick = { navController.navigate(metro.route) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //  Banner
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
                        append("recharge your metro card on ")
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
fun MetroItem(metroData: MetroData, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = metroData.logoRes),
            contentDescription = metroData.name,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = metroData.name,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}




@Composable
fun ChennaiMetroScreen() {
    // Implement Chennai Metro screen
    Text("Chennai Metro Screen")
}

@Composable
fun DelhiMetroScreen() {
    // Implement Delhi Metro screen
    Text("Delhi Metro Screen")
}

@Composable
fun KochiMetroScreen() {
    // Implement Kochi Metro screen
    Text("Kochi Metro Screen")
}


