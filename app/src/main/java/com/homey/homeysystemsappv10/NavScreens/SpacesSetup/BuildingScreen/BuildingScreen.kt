package com.homey.viewmodeltester.SpacesSetup.BuildingScreen

import android.util.Log
import androidx.compose.runtime.Composable

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.homey.homeysystemsappv10.NavRoutes
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BuildingsViewModel
import com.homey.viewmodeltester.SpacesSetup.SearchButtonIcon
import com.homey.viewmodeltester.SpacesSetup.spacesMainScreen

@Composable
fun buildingScreen(
    viewModel: BuildingsViewModel = viewModel(),
    navController: NavController
) {
    spacesMainScreen(
        title = "Buildings",
        fabButtonClick = {
            viewModel.onAddClick()
            Log.d("Message", "The button was clicked")
        },
        itemArray = viewModel.buildingArray,
        isDialogShown = viewModel.isDialogShown,
        onCancelClick = { viewModel.onCancelClick() },
        addSpace = { viewModel.addBuilding() },
        onCardClick = { spaceName ->
            Log.d("SpaceName", "Space name: $spaceName")
            navController.navigate(NavRoutes.Rooms.route + "/$spaceName")
        },
        spaceName = viewModel.buildingName,
        onSpaceNameChange = {viewModel.buildingName = it},
        onBackButtonPressed = {navController.popBackStack()},
        leftAction = { SearchButtonIcon() }
    )
}
