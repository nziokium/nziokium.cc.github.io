package com.homey.viewmodeltester.SpacesSetup.RoomScreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.homey.homeysystemsappv10.NavRoutes
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.analyticsButton
import com.homey.viewmodeltester.SpacesSetup.spacesMainScreen

@Composable
fun roomsScreen(
    navController: NavHostController,
    viewModel: RoomsViewModel // Initialize RoomViewModel with buildingName
) {
    spacesMainScreen(
        //Changed for the culture
        title = viewModel.buildingName,

        fabButtonClick = { viewModel.onAddClick() },
        itemArray = viewModel.roomList,
        isDialogShown = viewModel.isDialogShown,
        onCancelClick = { viewModel.onCancelClick() },
        addSpace = { viewModel.addRoom() },
        onCardClick = { spaceName ->
            Log.d("SpaceName", "Space name: $spaceName")
            navController.navigate(NavRoutes.IndividualRoomsScreen.route + "/$spaceName" + "/${viewModel.buildingName}")
        },
        spaceName = viewModel.roomName,
        onSpaceNameChange = {viewModel.roomName = it},
        onBackButtonPressed = { navController.popBackStack() },
        leftAction = { analyticsButton() }
    )
}