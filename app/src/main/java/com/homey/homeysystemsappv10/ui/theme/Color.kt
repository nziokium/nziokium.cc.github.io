package com.homey.homeysystemsappv10.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val greenStart = Color(0xFF9AD77D)
val greenEnd = Color(0xFF8E936F)

val blackStart = Color(0xFF596C6C)
val blackEnd =Color(0xFF475353)

@Composable
fun getHorizontalGradientColor(startColor: Color, endColor: Color, fraction: Float): Color {
    return Color(
        red = lerp(startColor.red, endColor.red, fraction),
        green = lerp(startColor.green, endColor.green, fraction),
        blue = lerp(startColor.blue, endColor.blue, fraction),
        alpha = lerp(startColor.alpha, endColor.alpha, fraction)
    )
}

private fun lerp(startValue: Float, endValue: Float, fraction: Float): Float {
    return startValue + fraction * (endValue - startValue)
}