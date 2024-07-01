package com.example.ekopay.bottomnav


import GreenCreditApp
import android.view.SurfaceControl.Transaction
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ekopay.HelloWorld
import com.example.ekopay.ui.theme.HelloWorld6

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
            HelloWorld()
        }
        composable(route = BottomBarScreen.Shopping.route)
        {
           HelloWorld6()
        }
    }
}