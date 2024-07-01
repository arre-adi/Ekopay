package com.example.ekopay.bottomnav

import com.example.ekopay.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.home_active,
        icon_focused = R.drawable.home
    )


    object History: BottomBarScreen(
        route = "history",
        title = "History",
        icon = R.drawable.history,
        icon_focused = R.drawable.history_active
    )


    object Shopping: BottomBarScreen(
        route = "shopping",
        title = "Shopping",
        icon = R.drawable.bag,
        icon_focused = R.drawable.bag_active )

    object Trade: BottomBarScreen(
        route = "trade",
        title = "Trade",
        icon = R.drawable.trade,
        icon_focused = R.drawable.trade_active
    )

}