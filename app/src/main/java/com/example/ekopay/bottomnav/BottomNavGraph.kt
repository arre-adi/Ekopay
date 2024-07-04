package com.example.ekopay.bottomnav


import GreenCreditApp
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ekopay.HistoryScreen
import com.example.ekopay.ShoppingScreen

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
            GreenCreditApp()
        }
        composable(route = BottomBarScreen.History.route)
        {
            HistoryScreen()
        }
        composable(route = BottomBarScreen.Shopping.route)
        {
           ShoppingScreen()
        }
    }
}