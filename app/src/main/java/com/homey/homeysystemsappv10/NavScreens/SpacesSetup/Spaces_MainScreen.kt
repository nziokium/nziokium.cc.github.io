package com.homey.viewmodeltester.SpacesSetup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.homey.homeysystemsappv10.BackButtonIcon
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.CustomDialog
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BuildingsViewModel
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.TopScreenBar
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen.AllRoomsViewModel
import com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen.RoomToBuilding
import com.homey.homeysystemsappv10.R
import com.homey.homeysystemsappv10.ui.theme.*


//Spaces Screen Blueprint

@Composable
fun spacesMainScreen(
    title: String,
    fabButtonClick: () -> Unit,
    itemArray: List<String>, // Change the type to Any
    isDialogShown: Boolean,
    onCancelClick: () -> Unit,
    addSpace: () -> Unit = {},
    onCardClick: (String) -> Unit = {},
    spaceName: String = "",
    onSpaceNameChange: (String) -> Unit = {},
    onBackButtonPressed: () -> Unit = {},
    showBuildingListCard: @Composable () -> Unit = {},
    leftAction: @Composable () -> Unit = {}
) {



        Scaffold(
            topBar = {
                TopScreenBar("${title}", {
                    BackButtonIcon(
                        onClick = {
                            onBackButtonPressed()
                        }
                    )
                }, { leftAction() })
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { fabButtonClick() },
                    shape = CircleShape
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add"

                    )
                }
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                ) {
                    items(itemArray.chunked(2)) { rowItems ->
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(rowItems) { item ->
                                Card(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = getHorizontalGradientColor(blackStart, blackEnd, 0.5f)
                                    ),
                                    modifier = Modifier
                                        .height(160.dp)
                                        .width(175.dp)
                                        .padding(8.dp)
                                        .clickable {
                                            onCardClick(item)
                                            Log.d("Message", "card clicked")
                                        }
                                ) {

                                    Text(item, modifier = Modifier.padding(16.dp),
                                        color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
            if (isDialogShown) {
                CustomDialog(
                    onDismiss = { onCancelClick() },
                    onCancel = { onCancelClick() },
                    onDone = { addSpace() },
                    title = title,
                    spaceName = spaceName,
                    onSpaceNameChange = onSpaceNameChange,
                    showBuildingListCard = showBuildingListCard
                )
            }
        }
    }


    //Create the Search Icon
    @Composable
    fun SearchButtonIcon() {
        IconButton(onClick = {/*TODO*/ }) {
            Image(
                painterResource(R.drawable.carbon_search),
                contentDescription = "SearchButton"
            )
        }

    }

    //Imported function
    @Composable
    fun BackButtonIcon(
        onClick: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick() }
        ) {
            Image(
                painterResource(R.drawable.backicon),
                contentDescription = "Back Action"
            )
        }
    }

