package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BuildingsViewModel
import com.homey.viewmodeltester.SpacesSetup.RoomScreen.RoomsViewModel
import com.homey.viewmodeltester.SpacesSetup.spacesMainScreen

@Composable
fun allRoomsScreen(navController: NavController,
                   viewModel: AllRoomsViewModel = viewModel()) {


    spacesMainScreen(
        title = "Rooms",
        fabButtonClick = { viewModel.onAddClick() },
        itemArray = viewModel.roomList,
        isDialogShown = viewModel.isDialogShown,
        onCancelClick = { viewModel.onCancelClick() },
        addSpace = {  },
        showBuildingListCard = { buildingsListCard() },
        onBackButtonPressed = { navController.popBackStack() }



    )
}


@Composable
fun RoundCheckboxList(values: List<String>) {
    var checkedIndex by remember { mutableStateOf(-1) }



    Column (
        modifier = Modifier
            .fillMaxSize()
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                values.forEachIndexed { index, value ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = index == checkedIndex,
                            onCheckedChange = {
                                checkedIndex = if (it) index else -1
                            },
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape) // Round the checkbox
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // Add space between checkbox and label
                        Text(text = value)


                    }
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun buildingsListCard() {
    val viewModel = BuildingsViewModel()

    val options = viewModel.buildingArray
    RoundCheckboxList(values = options)
}