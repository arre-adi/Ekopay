import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekopay.R
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1

@Composable @Preview(showSystemUi = true, showBackground = true)
fun GreenCreditApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        WelcomeBar()
        QuickActions()
        EarnCreditsSection()
        WhatIsGreenCreditCard()
        ShopGreenSection()
    }
}

@Composable
fun WelcomeBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green1)
            .padding(16.dp),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Green1)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(Icons.Default.Person, contentDescription = "User")
            Column {
                Text("hello anon,", color = Color.White)
                Text("Welcome Back!", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
            .fillMaxWidth()
                .background(Black1)

        ) {
            Text("BALANCE", color = Color.White)
            Text("400 GC", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}



@Composable
fun QuickActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        QuickActionItem(icon = Icons.Default.Home, text = "Scan QR")
        QuickActionItem(icon = Icons.Default.Home, text = "Pay \r\n Contacts")
        QuickActionItem(icon = Icons.Default.Home, text = "Check \r\n Balance")
        QuickActionItem(icon = Icons.Default.ShoppingCart, text = "Shopping")
    }
}

@Composable
fun EarnCreditsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Earn Credits >", color = Green1)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            EarnCreditItem(icon = Icons.Default.Home, text = "Deposit\r\n PET")
            EarnCreditItem(icon = Icons.Default.Home, text = "Register\r\n Energy Meter")
            EarnCreditItem(icon = Icons.Default.Home, text = "Metro \r\n Recharge")
            EarnCreditItem(icon = Icons.Default.Home, text = "Crowd \r\n Funding")
        }
    }
}

@Composable
fun WhatIsGreenCreditCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            Text("What is green credit?", color = Color.White)
            Text("Know more >", color = Green1)
        }
    }
}

@Composable
fun ShopGreenSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Shop Green >", color = Green1)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopItem(image = painterResource(id = R.drawable.imgapple), name = "Organic Soap", price = "$5")
            ShopItem(image = painterResource(id = R.drawable.imgapple), name = "Candles", price = "$10")
            ShopItem(image = painterResource(id = R.drawable.imgapple), name = "Organic Apples", price = "$20")
            }
    }
}

@Composable
fun QuickActionItem(icon: ImageVector, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EarnCreditItem(icon: ImageVector, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ShopItem(image: Painter, name: String, price: String) {
    Column(
        modifier = Modifier.width(100.dp)
    ) {
        Image(
            painter = image,
            contentDescription = name,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = price,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}