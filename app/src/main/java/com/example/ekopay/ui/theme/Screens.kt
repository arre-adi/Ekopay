package com.example.ekopay.ui.theme

sealed class Screens(val route:String) {

    object submitEcoBrick : Screens("submit_eco_brick")
    object learning : Screens("learn_more")

}