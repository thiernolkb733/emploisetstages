package com.example.emploisetstages.ui.navigation

sealed class Screen(val route: String) {
    object Offers : Screen("offers")
    object OfferDetail : Screen("offer_detail/{offerId}") {
        fun createRoute(offerId: Int) = "offer_detail/$offerId"
    }
    object Applications : Screen("applications")
    object Dashboard : Screen("dashboard")
}