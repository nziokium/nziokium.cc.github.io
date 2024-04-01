package com.homey.homeysystemsappv10.NavScreens.SpacesSetup.AllRoomsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

fun allRoomsScreen() {

}


@Composable
fun RoundCheckboxList(values: List<String>) {
    var checkedIndex by remember { mutableStateOf(-1) }

    Column {
        values.forEachIndexed { index, value ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = index == checkedIndex,
                    onCheckedChange = {
                        checkedIndex = if (it) index else -1
                    },
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape) // Round the checkbox
                )
                Spacer(modifier = Modifier.width(8.dp)) // Add space between checkbox and label
                Text(text = value)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RoundCheckboxListScreen() {
    val options = listOf("Building 1", "Building 2", "Building 3")

    RoundCheckboxList(values = options)
}