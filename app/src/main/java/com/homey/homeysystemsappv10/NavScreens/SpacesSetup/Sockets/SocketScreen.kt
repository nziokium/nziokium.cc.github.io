package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.Sockets

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BackButton
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.TopScreenBar
import com.homey.viewmodeltester.SpacesSetup.BackButtonIcon
import com.patrykandpatrick.vico.core.axis.AxisPosition

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SocketScreen(viewModel: SocketsViewModel = viewModel()) {



    Scaffold(
        topBar =
        {
            TopScreenBar(
                "Socket 1",
                { BackButton(onClick = {/*TODO*/ }) }
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
                    modifier = Modifier
                        .height(40.dp)
                ) {
                    Text(
                        "Value in Watts ",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.8f),
                        style = TextStyle(fontSize = 24.sp)
                    )

                    Text(
                        "${viewModel.sumValue}",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(0.2f),
                        style = TextStyle(fontSize = 24.sp)
                    )


                }

            }
        }
    }
}


