package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.IndividualRooms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.homey.homeysystemsappv10.NavRoutes
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BackButton
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.TopScreenBar
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.analyticsButton
import com.homey.homeysystemsappv10.R

@Composable
fun individualRoomsScreen(
    navController: NavController,
    viewModel: IndividualRoomsViewModel
) {


    val numbers = listOf(1, 2, 3, 4, 5)
    Scaffold(
        topBar =
        {
            TopScreenBar(
                text = viewModel._roomName,
                leftAction = { BackButton(onClick = {navController.popBackStack() }) }

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            genericCard(
                title = "Sockets"
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.Center
                ) {
                    items(viewModel.socketList) { socket ->
                        Card {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(NavRoutes.SocketScreen.route + "/$socket" + "/${viewModel._roomName}" +"/${viewModel._buildingName}")
                                    }
                            ) {
                                Text(
                                    socket,
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                                IconButton(
                                    onClick = { /*TODO*/ } // Invoke the onClick lambda
                                ) {
                                    Image(
                                        painterResource(R.drawable.ic_round_navigate_next),
                                        contentDescription = "BackButton"
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun genericCard(
    title: String = "",
    content: @Composable () -> Unit = {}
) {
    Text(
        title,
        style = TextStyle(fontSize = 24.sp)
    )
    Spacer(
        modifier = Modifier
            .height(4.dp)
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        content()
    }
}