package com.homey.homeysystemsappv10.NavScreens.HomeScreen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.foundation.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.homey.homeysystemsappv10.NavRoutes
import com.homey.homeysystemsappv10.TrialChart
import com.homey.homeysystemsappv10.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.res.fontResource
import com.homey.homeysystemsappv10.ui.theme.blackEnd
import com.homey.homeysystemsappv10.ui.theme.blackStart
import com.homey.homeysystemsappv10.ui.theme.getHorizontalGradientColor


@Composable
fun HomeScreen(navController: NavHostController) {
    // Remember the scrollState of the Constraint Layout
    val scrollState = rememberScrollState()




    ConstraintLayout(
        modifier = Modifier.padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        val (graphCard,
            greetingText,
            consumptionText,
            deviceText,
            spacesCard) = createRefs()


        //Add the Good Afternoon Text
        Text("Good Afternoon",

            fontSize = 34.sp,
            modifier = Modifier
                .constrainAs(greetingText) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
        )

        Text("Your Consumption",
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(consumptionText) {
                    top.linkTo(greetingText.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
        )


        //Add the graph card
        ElevatedCard(
            shape = RoundedCornerShape(size = 24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Yellow
            ),
            modifier = Modifier.height(300.dp)
                .fillMaxWidth()
                .constrainAs(graphCard) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(consumptionText.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }
                .border(width = 1.dp, Color.Black, shape = RoundedCornerShape(24.dp))
                .clickable {  navController.navigate(NavRoutes.AnalyticsScreen.route)
                           Log.d("HELOO","NAVIGATED TO ANALYTICS SCREEN")},
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )


        ) {


            Text(
                "Today",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "4.2kWh ",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TrialChart()

            Spacer(modifier = Modifier.height(8.dp))


        }


        //Add a text for Spaces
        Text("Your Spaces",
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(deviceText) {
                    top.linkTo(graphCard.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }
        )

        //Create a card with buttons for navigating to Spaces
        //You'll have to use IntrinsicSize Measurements for it
        Card(
            shape = RoundedCornerShape(size = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier.height(88.dp)
                .fillMaxWidth()
                .constrainAs(spacesCard) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(deviceText.bottom, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                }



        ) {
            //Add the building button
            Card(
                onClick = { navController.navigate(NavRoutes.Building.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f, fill = true),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = getHorizontalGradientColor(blackStart, blackEnd,0.5f)
                )

            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.building),
                        contentDescription = "Building",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.1f)
                            .padding(4.dp)
                    )
                    Text(
                        "Buildings",

                        modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 3.dp)

                    )
                    Image(
                        painter = painterResource(R.drawable.ic_round_navigate_next),
                        contentDescription = null,
                        modifier = Modifier

                            .fillMaxHeight()
                            .weight(0.1f)
                    )
                }


            }

            Spacer(modifier = Modifier.height(2.dp))
            //Add the room Button
            Card(
                onClick = {navController.navigate(NavRoutes.AllRoomsScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f, fill = true),
                shape = RoundedCornerShape(
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = getHorizontalGradientColor(blackStart, blackEnd,0.5f)
                )


            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.room),
                        contentDescription = "Building",
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(0.1f)
                            .padding(start = 4.dp)
                    )
                    Text(
                        "Rooms",

                        modifier = Modifier
                            .weight(0.8f)
                            .padding(start = 3.dp)

                    )
                    Image(
                        painter = painterResource(R.drawable.ic_round_navigate_next),
                        contentDescription = null,
                        modifier = Modifier

                            .fillMaxHeight()
                            .weight(0.1f)
                    )
                }




            }
        }

    }

}



