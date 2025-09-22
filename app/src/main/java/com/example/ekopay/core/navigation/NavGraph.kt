package com.example.ekopay.core.navigation

import EkopayApp
import android.content.Context
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.ekopay.feature.onboarding.ui.OnboardingFlow
import com.example.ekopay.feature.payments.ui.paymentcompletescreen
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// DataStore for onboarding preference
private val Context.dataStore by preferencesDataStore("user_preferences")
private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")

@Composable
fun BottomNavGraph(navController: NavHostController) {
    val context = LocalContext.current

    // Check if onboarding is completed using DataStore
    val onboardingCompleted by context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_COMPLETED] ?: false
        }
        .collectAsState(initial = false)

    NavHost(
        navController = navController,
        startDestination = if (onboardingCompleted) BottomBarScreen.Home.route else "onboarding_flow",
        modifier = Modifier.navigationBarsPadding()
    ) {
        // Modern onboarding flow - single composable that handles everything
        composable("onboarding_flow") {
            OnboardingFlow(
                onComplete = {
                    // Save onboarding completion and navigate to home
                    saveOnboardingCompletion(context)
                    navController.navigate(BottomBarScreen.Home.route) {
                        popUpTo("onboarding_flow") { inclusive = true }
                    }
                },
                onSkip = {
                    // Save onboarding completion and navigate to home
                    saveOnboardingCompletion(context)
                    navController.navigate(BottomBarScreen.Home.route) {
                        popUpTo("onboarding_flow") { inclusive = true }
                    }
                }
            )
        }

        // Main app screens
        composable(route = BottomBarScreen.Home.route) {
          EkopayApp(navController)
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
            BengaluruMetroUI(navController)
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

        composable("payment_done") {
            paymentcompletescreen()
        }

        composable("crowwwd_funding") {
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

        // Debug route - only include in debug builds
        // Remove or comment out in production
        composable("debug_onboarding") {
            OnboardingFlow(
                onComplete = {
                    navController.navigateUp()
                },
                onSkip = {
                    navController.navigateUp()
                }
            )
        }
    }
}



private fun saveOnboardingCompletion(context: Context) {
    CoroutineScope(Dispatchers.IO).launch {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = true
        }
    }
}

// Extension function for easy onboarding reset (useful for testing)
suspend fun resetOnboarding(context: Context) {
    context.dataStore.edit { preferences ->
        preferences[ONBOARDING_COMPLETED] = false
    }
}