package com.homey.homeysystemsappv10

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.homey.homeysystemsappv10.NavScreens.AnalyticsScreen.AnalyticsScreen
import com.homey.homeysystemsappv10.NavScreens.HomeScreen.HomeScreen
import com.homey.homeysystemsappv10.NavScreens.LogInScreen
import com.homey.viewmodeltester.SpacesSetup.BuildingScreen.buildingScreen
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.RoomsViewModel
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.roomScreen

//Navigation2
@Composable
fun StartNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LogInScreen.route,
    ) {
        composable(NavRoutes.LogInScreen.route) {
            LogInScreen(navController = navController)
        }
        composable(NavRoutes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.Building.route) {
            buildingScreen(navController = navController)
        }
        composable(NavRoutes.Rooms.route + "/{buildingName}") { backStackEntry ->
            val buildingName = backStackEntry.arguments?.getString("buildingName")
            if (buildingName != null) {
                roomScreen(
                    navController = navController,
                    viewModel = RoomsViewModel(buildingName)
                )
            } else {
                roomScreen(
                    navController = navController,
                    viewModel = RoomsViewModel()
                )
            }


        }

        composable(NavRoutes.AnalyticsScreen.route) {
            AnalyticsScreen(navController = navController)
        }

    }
}