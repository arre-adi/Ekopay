package com.example.ekopay.core.navigation


import GreenCreditApp
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ekopay.feature.crowdfunding.ui.AddMoney
import com.example.ekopay.feature.crowdfunding.ui.CrowdFundingPaymentScreen
import com.example.ekopay.deadscreen.EcoBrickScreen
import com.example.ekopay.feature.trading.ui.FinalTradingScreen
import com.example.ekopay.feature.crowdfunding.ui.FundingPreview
import com.example.ekopay.feature.payments.ui.TransactionHistoryScreen
import com.example.ekopay.feature.learning.ui.LearningScreen
import com.example.ekopay.ProductCardfinal
import com.example.ekopay.feature.qrscan.ui.QRScannerScreen
import com.example.ekopay.feature.shop.ui.ShoppingScreen
import com.example.ekopay.feature.trading.ui.TradingScreen
import com.example.ekopay.feature.qrscan.ui.SuccessScreen
import com.example.ekopay.feature.metro.ui.BengaluruMetroUI
import com.example.ekopay.feature.metro.ui.ChennaiMetroScreen
import com.example.ekopay.feature.metro.ui.DelhiMetroScreen
import com.example.ekopay.feature.metro.ui.KochiMetroScreen
import com.example.ekopay.feature.metro.ui.MetroPriceScreen
import com.example.ekopay.feature.metro.ui.SelectMetroScreen
import com.example.ekopay.feature.payments.ui.paymentcompletescreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    val shouldShowOnboarding = remember { mutableStateOf(true) }

    NavHost(
        navController = navController,
        startDestination = if (shouldShowOnboarding.value) "onboarding1" else BottomBarScreen.Home.route,
        modifier = Modifier.navigationBarsPadding()
    ) {
        composable("onboarding1") {
            OnboardingScreen1 {
                navController.navigate("onboarding2")
            }
        }
        composable("onboarding2") {
            OnboardingScreen2 {
                navController.navigate("onboarding3")
            }
        }
        composable("onboarding3") {
            OnboardingScreen3 {
                navController.navigate(BottomBarScreen.Home.route) {
                    popUpTo("onboarding1") { inclusive = true }
                }
                shouldShowOnboarding.value = false
            }
        }

        composable(route = BottomBarScreen.Home.route) {
            GreenCreditApp(navController)
        }


        composable(route = BottomBarScreen.History.route) {
            TransactionHistoryScreen()
        }
        composable(route = BottomBarScreen.Shopping.route) {
            ShoppingScreen(navController)
        }
        composable(route = BottomBarScreen.Trade.route) {
            TradingScreen(navController)
        }
        composable("submitEcoBrick") {
            EcoBrickScreen()
        }
        composable("learning") {
            LearningScreen()
        }
        composable("qrscanner") {
            QRScannerScreen(navController)
        }
        composable("success") {
            SuccessScreen(navController)
        }
        composable("selectmetro") {
            SelectMetroScreen(navController)
        }
        composable("b_metro") {
            BengaluruMetroUI(navController)  // Pass navController to allow navigation
        }

        composable("chennai_metro") {
            ChennaiMetroScreen()
        }
        composable("delhi_metro") {
            DelhiMetroScreen()
        }
        composable("kochi_metro") {
            KochiMetroScreen()
        }

        composable("payment_done"){
            paymentcompletescreen()
        }

        composable("crowwwd_funding"){
            FundingPreview(navController)
        }
        composable(
            route = "finalTrading/{amount}",
            arguments = listOf(navArgument("amount") { type = NavType.StringType })
        ) { backStackEntry ->
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            FinalTradingScreen(navController, amount)
        }


        composable("productCardFinal") {
            ProductCardfinal()
        }

        // Update Metro Price Screen to accept metroCardNumber and amount as arguments
        composable(
            route = "metro_price/{metroCardNumber}/{amount}",
            arguments = listOf(
                navArgument("metroCardNumber") { type = NavType.StringType },
                navArgument("amount") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val metroCardNumber = backStackEntry.arguments?.getString("metroCardNumber") ?: ""
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            MetroPriceScreen(navController, metroCardNumber, amount)
        }

        composable(
            route = "add_money/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            AddMoney(navController, name)
        }

        composable(
            route = "crowdfunding_payment/{name}/{amount}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("amount") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            CrowdFundingPaymentScreen(navController, name, amount)
        }
    }
}
