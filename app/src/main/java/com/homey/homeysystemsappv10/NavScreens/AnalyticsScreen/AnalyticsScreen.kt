package com.homey.homeysystemsappv10.NavScreens.AnalyticsScreen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.homey.homeysystemsappv10.NavRoutes

import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.BackButton
import com.homey.homeysystemsappv10.NavScreens.BuildingScreen.TopScreenBar
import com.homey.homeysystemsappv10.R
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberEndAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.ExtraStore
import com.patrykandpatrick.vico.core.model.columnSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(navController: NavController){

    val scrollState = rememberScrollState()

    Scaffold (
        topBar = {
            TopScreenBar(
                "My Consumption",
                { BackButtonIcon(
                    onClick = {
                        navController.popBackStack()
                    }
                )
                }
            )
        }
    ){padding ->

        Column(
            modifier = Modifier.padding(padding, )
                .verticalScroll(scrollState),


            ) {
            Spacer(modifier = Modifier.height(8.dp))

            //The graph for the Analytics of the socket
            AnalyticsGraph()


            Spacer(modifier = Modifier.height(42.dp))

            Text(
                "BIGGEST CONSUMERS(ROOMS)",
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(start = 16.dp)

            )
            RoomConsumers()

            Button(
                onClick = {navController.popBackStack()}
            ){
                Text("Go back")
            }
        }

    }
}

@Composable
fun AnalyticsGraph(
    text: String = "Today's Date") {

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Yellow
        )
    ) {

        Text(
            text,
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

    }
}

@Composable
fun RoomConsumers(){
    val itemsList = listOf(1,2,3,4,5)

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
    ) {
        itemsList.forEach {  index ->
            RoomCards(index)
            Spacer(modifier = Modifier.height(2.dp))

        }
    }
}

@Composable
fun RoomCards(item: Int){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RectangleShape
    ) {
        Text(
            "Room $item",
            fontSize = 17.sp,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun TrialChart(viewModel: AnalyticsScreenViewModel = viewModel()) {
    val modelProducer = remember { CartesianChartModelProducer.build() }

    // Extract value from the ViewModel
    val number = viewModel.number.value

    // Check if number and number.value are not null before conversion
    val floatValue = try {
        number.value.toFloat()
    } catch (e: NumberFormatException) {
        2f
    }

    val data = mapOf(
        "Mon" to floatValue,
        "Tue" to 4f,
        "Wed" to 8f,
        "Thur" to 3f,
        "Fri" to 0f,
        "Sat" to 3.5f,
        "Sun" to 2.4f
    )

    val labelListKey = remember { ExtraStore.Key<List<String>>() }

    val axisFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { x, chartValues, _ ->
            chartValues.model.extraStore[labelListKey][x.toInt()] ?: ""
        }

    LaunchedEffect(Unit) {
        // Launch a coroutine for any asynchronous or time-consuming task
        withContext(Dispatchers.Default) {
            // Update the chart model or perform any other task
            modelProducer.tryRunTransaction {
                columnSeries { series(data.values) }
                updateExtras { it[labelListKey] = data.keys.toList() }
            }
        }
    }

    if (viewModel.isDataLoaded) {
        CartesianChartHost(
            rememberCartesianChart(
                rememberColumnCartesianLayer(),
                endAxis = rememberEndAxis(),
                bottomAxis = rememberBottomAxis(
                    valueFormatter = axisFormatter
                ),
            ),
            modelProducer,
            modifier = Modifier
                .padding(4.dp)
        )
    }
}

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


