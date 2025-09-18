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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ekopay.R
import com.example.ekopay.core.navigation.BottomBarScreen
import com.example.ekopay.core.components.ActionButton
import com.example.ekopay.core.components.InfoCard
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1
import com.example.ekopay.ui.theme.White1


@Preview(showBackground = true)
@Composable
fun GreenCreditAppPreview() {
    val navController = rememberNavController()
    GreenCreditApp(navController = navController)
}






@Composable
fun GreenCreditApp(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(White1)
            .padding(12.dp)

    ) {
        item{InfoCard(
            userName = "Welcome Back",
            userGreeting = "hello john",
            balanceText = "BALANCE",
            balanceAmount = "15 GC"
        )}
        item{QuickActions(navController)}
        item{EarnCreditsSection(navController)}
        item{LearnSection(navController)}
        item{ShopGreenSection()}
    }
}




@Composable
fun QuickActions(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActionButton(
            icon = ImageVector.vectorResource(id = R.drawable.scanner),
            text = "Scan QR",
            bgcolor = Black1,
            onClick = {  navController.navigate("qrscanner")}
        )
        ActionButton(
            icon = ImageVector.vectorResource(id = R.drawable.arrow_down),
            text = "Pay\r\nContacts",
            bgcolor = Black1,
            iconSize = 60.dp,
            onClick = {}
        )
        ActionButton(
            icon = ImageVector.vectorResource(id = R.drawable.empty_wallet),
            text = "Check\r\nBalance",
            bgcolor = Black1,
            onClick = {  }
        )
    }
}

@Composable
fun EarnCreditsSection(navController: NavController) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            "Earn Credits",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight(600),
            color = Green1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
//            ActionButtons(
//                icon = ImageVector.vectorResource(id = R.drawable.noun_water_bottle_2583203),
//                text = "Deposit\nEco Brick",
//                iconSize = 40.dp,
//                bgcolor = Green1,
//                onClick = { navController.navigate(Screens.submitEcoBrick.route)}
//            )

//            ActionButtons(
//                icon = ImageVector.vectorResource(id = R.drawable.energy_meter),
//                text = "Register\nEnergy Meter",
//                bgcolor = Green1,
//                onClick = { /*TODO*/}
//            )
            ActionButton(
                icon = ImageVector.vectorResource(id = R.drawable.noun_metro_3866657),
                text = "Metro\nRecharge",
                bgcolor = Green1,
                onClick = { navController.navigate("selectmetro")}
            )
            ActionButton(
                icon = ImageVector.vectorResource(id = R.drawable.noun_crowdfunding_850697),
                text = "Crowd\nFunding",
                bgcolor = Green1,
                onClick = {navController.navigate("crowwwd_funding")}
            )

            ActionButton(
                icon = ImageVector.vectorResource(id = R.drawable.bag_2),
                text = "Shopping",
                bgcolor = Green1,
                onClick = {  navController.navigate(BottomBarScreen.Shopping.route)}
            )
        }
    }
}


@Composable
fun LearnSection(navController: NavController) {

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(listOf("What is green credit?", "How to earn green credit?")) { question ->
            GreenCreditInfoCard(question = question, navController = navController)
        }}
}

@Composable
fun ShopGreenSection() {
    Column(modifier = Modifier
        .padding(bottom = 16.dp)) {
        Text("Shop Green",
            style = MaterialTheme.typography.bodyLarge,
            color = Green1,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .padding(top =0.dp, bottom = 16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ShopItem(
                image = painterResource(id = R.drawable.img_soap),
                name = "Organic Soap",
                price = "₹50"
            )
            ShopItem(
                image = painterResource(id = R.drawable.img_candle),
                name = "Candles",
                price = "₹10"
            )
            ShopItem(
                image = painterResource(id = R.drawable.imgapple),
                name = "Organic Apples",
                price = "₹200"
            )
        }
    }
}




@Composable
fun GreenCreditInfoCard(question: String, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable(onClick = {navController.navigate("learning")})
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
                    tint = White1,
                    modifier = Modifier
                        .size(45.dp)
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
                    color = White1
                )
                    Text(
                        "Know more",
                        textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline,
                        color = White1
                    )
                }
            }
        }
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

