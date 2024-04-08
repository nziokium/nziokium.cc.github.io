package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.Sockets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BackButton
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.TopScreenBar


@Composable
fun socketScreen(
    navController: NavController,
    viewModel: SocketsViewModel = viewModel()) {

    Scaffold(
        topBar =
        {
            TopScreenBar(
                "Socket 1",
                { BackButton(onClick = {navController.popBackStack()}) }
            )
        }
    ) { innerPadding ->
        (16.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)

        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Turn Socket ${viewModel.name}",
                        modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 8.dp),
                        style = TextStyle(fontSize = 24.sp)
                    )
                    Switch(
                        checked = viewModel.bool,
                        onCheckedChange = { newState ->
                            viewModel.updateSwitchState(newState)
                        },
                        modifier = Modifier
                            .weight(0.2f)
                    )
                }


            }


            Spacer(
                modifier = Modifier.height(24.dp)
                    .padding(innerPadding)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        "Value in Watts ",
                        modifier = Modifier
                            .padding(start = 8.dp),
                        style = TextStyle(fontSize = 24.sp)
                    )

                    Text(
                        "${viewModel.sumValue}",
                        modifier = Modifier
                            .padding(end = 8.dp),
                        style = TextStyle(fontSize = 24.sp)
                    )


                }

            }
        }
    }
}


