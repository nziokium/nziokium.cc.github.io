package com.homey.viewmodeltester.SpacesSetup.RoomScreen

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.homey.viewmodeltester.SpacesSetup.spacesMainScreen

@Composable
fun roomScreen(
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
        spaceName = viewModel.roomName,
        onSpaceNameChange = {viewModel.roomName = it},
        onBackButtonPressed = { navController.popBackStack() }
    )
}