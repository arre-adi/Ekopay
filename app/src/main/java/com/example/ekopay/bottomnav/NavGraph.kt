package com.example.ekopay.bottomnav


import GreenCreditApp
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ekopay.BottomBarScreen
import com.example.ekopay.EcoBrickScreen
import com.example.ekopay.TransactionHistoryScreen
import com.example.ekopay.LearningScreen
import com.example.ekopay.ShoppingScreen
import com.example.ekopay.ui.theme.Screens

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route)
        {
            GreenCreditApp(navController)
        }
        composable(route = BottomBarScreen.History.route)
        {
            TransactionHistoryScreen()
        }
        composable(route = BottomBarScreen.Shopping.route)
        {
           ShoppingScreen()
        }
        composable(route = Screens.submitEcoBrick.route){
            EcoBrickScreen()
        }
        composable(route = Screens.learning.route){
            LearningScreen()
        }
    }
}