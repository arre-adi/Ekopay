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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
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
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(12.dp)

    ) {
        item{WelcomeBar()}
        item{QuickActions()}
        item{EarnCreditsSection()}
        item{WhatIsGreenCreditCard()}
        item{ShopGreenSection()}
    }
}

@Composable
fun WelcomeBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.frame_1),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .matchParentSize()
                .background(Green1,
                        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    ImageVector.vectorResource(id = R.drawable.baseline_account_circle_24),
                    modifier = Modifier.size(50.dp)
                        .padding(end = 10.dp), contentDescription = "User"
                )
                Column {
                    Text(
                        "hello anon,",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        "Welcome Back!",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                Black1,
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(16.dp)

    ) {
        Text("BALANCE",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(400),
            color = Color.White)
        Text("400 GC",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight(600),
            color = Green1)
    }
}



@Composable
fun QuickActions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        QuickActionItem(icon = ImageVector.vectorResource(id = R.drawable.scanner), text = "Scan QR")
        QuickActionItem(icon = ImageVector.vectorResource(id = R.drawable.arrow_down), text = "Pay\r\nContacts")
        QuickActionItem(icon = ImageVector.vectorResource(id = R.drawable.empty_wallet), text = "Check\r\nBalance")
        QuickActionItem(icon = ImageVector.vectorResource(id = R.drawable.bag_2), text = "Shopping")
    }
}

@Composable
fun EarnCreditsSection() {
    Column(modifier = Modifier
        .padding(top =16.dp)){
        Text("Earn Credits",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(600),
            color = Green1,
            modifier = Modifier
                .padding(start = 16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            EarnCreditItem(icon = Icons.Default.Home, text = "Deposit\r\nPET")
            EarnCreditItem(icon = Icons.Default.Home, text = "Register\r\nEnergy Meter")
            EarnCreditItem(icon = Icons.Default.Home, text = "Metro\r\nRecharge")
            EarnCreditItem(icon = Icons.Default.Home, text = "Crowd\r\nFunding")
        }
    }
}

@Composable
fun WhatIsGreenCreditCard() {

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(listOf("What is green credit?", "How to earn green credit?")) { question ->
            GreenCreditInfoCard(question = question)
        }}
}

@Composable
fun GreenCreditInfoCard(question: String) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .height(120.dp)
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black1)
        ){
            Image(
                painter = painterResource(id = R.drawable.frame_2),
                contentDescription = "Green Credit Card Background",
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.TopStart,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.baseline_question_mark_24),
                    tint = Color.White,
                    modifier = Modifier.size(45.dp)
                        .background(color = Green1, shape = CircleShape)
                        .padding(10.dp),
                    contentDescription = "Question Mark"
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {Text(
                    question,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
                    Text(
                        "Know more",
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline,
                        color = Color.White
                    )
                }
            }
        }
    }
}




@Composable
fun ShopGreenSection() {
    Column(modifier = Modifier
        .padding(start = 16.dp, bottom = 16.dp)) {
        Text("Shop Green",
            style = MaterialTheme.typography.bodyLarge,
            color = Green1,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .padding(top =0.dp, bottom = 16.dp))

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
                .size(62.dp)
                .background(Black1, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Green1,
                modifier = Modifier.size(35.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            lineHeight = 14.sp,
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
                .size(62.dp)
                .background(Green1, shape = RoundedCornerShape(12.dp)),
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
            lineHeight = 14.sp,
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