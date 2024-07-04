package com.example.ekopay.bottomnav

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.ekopay.R

sealed class BottomBarScreen(
    val route: String,
    val icon: Int,
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        icon =  R.drawable.home,
    )


    object History: BottomBarScreen(
        route = "history",
        icon = R.drawable.history,
    )


    object Shopping: BottomBarScreen(
        route = "shop",
        icon = R.drawable.shop )

    object Trade: BottomBarScreen(
        route = "trade",
        icon = R.drawable.trade,
    )

}