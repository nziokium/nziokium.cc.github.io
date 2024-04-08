package com.homey.homeysystemsappv10

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.homey.homeysystemsappv10.NavScreens.HomeScreen.HomeScreen
import com.homey.homeysystemsappv10.NavScreens.LogInScreen
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen.allRoomsScreen
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.IndividualRooms.IndividualRoomsViewModel
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.IndividualRooms.individualRoomsScreen
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.Sockets.SocketsViewModel
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.Sockets.socketScreen
import com.homey.viewmodeltester.SpacesSetup.BuildingScreen.buildingScreen
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.RoomsViewModel
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.roomsScreen

//Navigation2
@Composable
fun StartNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HomeScreen.route,
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
                roomsScreen(
                    navController = navController,
                    viewModel = RoomsViewModel(buildingName)
                )
            } else {
                roomsScreen(
                    navController = navController,
                    viewModel = RoomsViewModel()
                )
            }


        }

        composable(NavRoutes.AnalyticsScreen.route) {
            AnalyticsScreen(navController = navController)
        }
        composable(NavRoutes.AllRoomsScreen.route){
            allRoomsScreen(navController = navController)
        }

        composable(NavRoutes.IndividualRoomsScreen.route + "/{roomName}" + "/{buildingName}") {
            backStackEntry ->
            val roomName = backStackEntry.arguments?.getString("roomName")
            val buildingName = backStackEntry?.arguments?.getString("buildingName")

            if (roomName != null && buildingName != null) {
                individualRoomsScreen(
                    navController = navController,
                    viewModel = IndividualRoomsViewModel(
                        buildingName = buildingName,
                        roomName = roomName
                    )
                )
            } else {
                individualRoomsScreen(
                    navController = navController,
                    viewModel = IndividualRoomsViewModel()
                )
            }
        }

        composable(NavRoutes.SocketScreen.route + "/{socketName}" + "/{roomName}"+"/{buildingName}"){
            backStackEntry ->
            val socketName = backStackEntry.arguments?.getString("socketName")
            val roomName = backStackEntry.arguments?.getString("roomName")
            val buildingName = backStackEntry.arguments?.getString("buildingName")



            if(socketName != null && roomName != null && buildingName != null){
                socketScreen(
                    navController = navController,
                    viewModel = SocketsViewModel(
                        socketName = socketName,
                        roomName = roomName,
                        buildingName = buildingName
                    )
                )
            }else{
                socketScreen(
                    navController = navController,
                    viewModel = SocketsViewModel()
                )
            }

        }
    }
}