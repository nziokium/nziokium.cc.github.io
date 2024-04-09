package com.homey.homeysystemsappv10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.homey.homeysystemsappv10.ui.theme.HomeySystemsAppV1_0Theme
import com.homey.homeysystemsappv10.ui.theme.getHorizontalGradientColor
import com.homey.homeysystemsappv10.ui.theme.greenEnd
import com.homey.homeysystemsappv10.ui.theme.greenStart


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        setContent {
            HomeySystemsAppV1_0Theme {
                // A surface container using the 'background' color from the theme
                val gradientFraction = 0.5f

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = getHorizontalGradientColor(greenStart, greenEnd,gradientFraction)
                ) {
                    StartNav()
                }
            }
        }
    }
}



