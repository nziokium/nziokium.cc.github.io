package com.homey.homeysystemsappv10

sealed class NavRoutes(val route: String) {
    object LogInScreen : NavRoutes("loginscreen")
    object HomeScreen : NavRoutes("homescreen")
    object Building : NavRoutes("buildingscreen")
    object AnalyticsScreen: NavRoutes("analyticsscreen")
    object Rooms : NavRoutes("roomscreen")
    object AllRoomsScreen: NavRoutes("allroomsScreen")

}