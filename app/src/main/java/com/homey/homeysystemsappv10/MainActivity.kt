package com.homey.homeysystemsappv10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.navigation.compose.NavHost
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.homey.homeysystemsappv10.NavScreens.HomeScreen.HomeScreen
import com.homey.homeysystemsappv10.NavScreens.LogInScreen
import com.homey.homeysystemsappv10.ui.theme.HomeySystemsAppV1_0Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeySystemsAppV1_0Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartNav()
                }
            }
        }
    }
}



