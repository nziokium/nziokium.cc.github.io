package com.homey.homeysystemsappv10.NavScreens.BuildingScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homey.homeysystemsappv10.R


@Composable
fun tester() {
    var count by remember { mutableStateOf(0) }

    // Function to increment the counter
    val incrementCounter: () -> Unit = {
        count++
        Log.d("Counter", "Counter value: $count")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopScreenBar(
            "Test",
            leftAction = { BackButton(onClick = { incrementCounter() }) }
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopScreenBar(
    text: String,
    leftAction: @Composable () -> Unit = {},
    rightAction: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = text)
        },
        navigationIcon = {
            leftAction()
        },
        actions = {
            rightAction()
        }
    )
}

@Composable

fun BackButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() } // Invoke the onClick lambda
    ) {
        Image(
            painterResource(R.drawable.backicon),
            contentDescription = "BackButton"
        )
    }
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun TopScreenBarPreview() {
    TopScreenBar("Buildings")
}