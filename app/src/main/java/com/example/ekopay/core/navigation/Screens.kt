package com.example.ekopay.core.navigation

import com.example.ekopay.R

sealed class Screens(
    val route:String
) {
    object submitEcoBrick : Screens("submit_eco_brick")
}

sealed class BottomBarScreen(
    val route: String,
    val icon: Int,
) {
    object Home: BottomBarScreen(
        route = "home",
        icon = R.drawable.ic_home,
    )


    object History: BottomBarScreen(
        route = "history",
        icon = R.drawable.ic_history,
    )


    object Shopping: BottomBarScreen(
        route = "shop",
        icon = R.drawable.ic_shop
    )

    object Trade: BottomBarScreen(
        route = "trade",
        icon = R.drawable.ic_trade,
    )

}