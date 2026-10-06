package com.example.emploisetstages.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.emploisetstages.ui.screens.offers.OffersScreen
import com.example.emploisetstages.ui.screens.applications.ApplicationsScreen
import com.example.emploisetstages.ui.screens.dashboard.DashboardScreen
import com.example.emploisetstages.ui.screens.offerdetail.OfferDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Offers.route,
        modifier = modifier
    ) {
        composable(Screen.Offers.route) {
            OffersScreen(
                onOffreClick = { offerId ->
                    navController.navigate(Screen.OfferDetail.createRoute(offerId))
                }
            )
        }
        composable(Screen.Applications.route) {
            ApplicationsScreen()
        }
        composable(Screen.Dashboard.route) {
            DashboardScreen()
        }
        composable(
            route = Screen.OfferDetail.route,
            arguments = listOf(navArgument("offerId") { type = NavType.IntType })
        ) { backStackEntry ->
            val offerId = backStackEntry.arguments?.getInt("offerId") ?: 0
            OfferDetailScreen(
                offerId = offerId,
                onBackClick ={ navController.popBackStack()}
            )
        }
    }
}