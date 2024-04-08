package com.homey.homeysystemsappv10

sealed class NavRoutes(val route: String) {
    object LogInScreen : NavRoutes("loginScreen")
    object HomeScreen : NavRoutes("homeScreen")
    object Building : NavRoutes("buildingScreen")
    object AnalyticsScreen: NavRoutes("analyticsScreen")
    object Rooms : NavRoutes("roomScreen")
    object AllRoomsScreen: NavRoutes("allRoomsScreen")
    object IndividualRoomsScreen: NavRoutes("individualRoomsScreen")
    object SocketScreen: NavRoutes("socketScreen")

}