package com.example.ekopay.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.draw.shadow
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ekopay.R
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1



@Composable
fun OnboardingScreen1(onNextClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.group_1737), contentDescription = null,
            modifier = Modifier
                .size(300.dp))
        Spacer(modifier = Modifier.height(32.dp))
        Text("Trade Green \n Save Green",
                style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(800),
            color = Black1
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text("Earn rewards by trading green credits and \n enjoy discounts on eco-friendly products.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(400),
            color = Black1
        )
        Spacer(modifier = Modifier.padding(top=100.dp))
        Button(
            onClick = onNextClick,
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
fun OnboardingScreen2(onNextClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.frame_4057), contentDescription = null,
            modifier = Modifier
                .size(300.dp))
        Spacer(modifier = Modifier.height(32.dp))
        Text("Track Trade \n Transform",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(800),
            color = Black1
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text("Manage your carbon footprint, trade credits,\n and unlock savings on sustainable goods.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(400),
            color = Black1
        )
        Spacer(modifier = Modifier.padding(top=100.dp))
        Button(
            onClick = onNextClick,
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
fun OnboardingScreen3(onFinish: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Image(painter = painterResource(id = R.drawable.invest), contentDescription = null,
            modifier = Modifier
                .size(300.dp))
        Spacer(modifier = Modifier.height(32.dp))
        Text("Eco-Friendly\nInvestments,\nReal Rewards",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight(800),
            color = Black1
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text("Align your investments with sustainability and \n get exclusive discounts on green products.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(400),
            color = Black1
        )
        Spacer(modifier = Modifier.padding(top=100.dp))
        Button(
            onClick = onFinish,
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
            Text("Get Started")
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            BottomNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.History,
        BottomBarScreen.Shopping,
        BottomBarScreen.Trade
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(60.dp)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
    ) {
                    Icon(
                painter = painterResource(id = screen.icon),
                tint = if (selected)Green1 else Black1,
                contentDescription = null
                )

    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun BottomNavPreview() {
    MainScreen()
}


