package com.example.ekopay.futurescreens

import ActionButtons
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.ekopay.R
import com.example.ekopay.WelcomeBar
import com.example.ekopay.ui.theme.Green1

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EcoBrickScreen() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {
        WelcomeBar(
            userName = "Anon",
            userGreeting = "1234 5698 3699 4565",
            balanceText = "PLASTIC SUBMIT",
            balanceAmount = "500 KG"
        )
        EcoBrickOptionSection()

    }
}




@Composable
fun EcoBrickOptionSection() {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
           ActionButtons(
                icon = ImageVector.vectorResource(id = R.drawable.baseline_add_24),
                text = "Deposit\nEco Brick",
               bgcolor = Green1,
                onClick = { }
            )
            ActionButtons(
                icon = ImageVector.vectorResource(id = R.drawable.clock),
                text = "History",
                bgcolor = Green1,
                onClick = { /*TODO*/}
            )
            ActionButtons(
                icon = ImageVector.vectorResource(id = R.drawable.track),
                text = "Track\n Package",
                bgcolor = Green1,
                onClick = { /*TODO*/}
            )
        }
    }








