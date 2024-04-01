package com.homey.homeysystemsappv10.NavScreens.BuildingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.homey.homeysystemsappv10.R


//Dialog for Adding the Buildings
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialog(
    onDismiss: ()-> Unit,
    onCancel: () -> Unit,
    onDone: () -> Unit,
    title: String,
    spaceName: String, // Parameter to receive building name
    onSpaceNameChange: (String) -> Unit

) {
    //Sheet state that fully expands when invoked
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = {
            //Closes the bottom sheet when pressed
            onDismiss()
        },
        sheetState = sheetState,

        //Drag handle composable
        dragHandle = {
            BottomSheetTopBar(onCancel= onCancel,
            onDone = onDone,
                title = "Add To $title"
        ) }
    ){
        addSpaceContent(spaceName,onSpaceNameChange)
    }
}

//Content for the Bottom sheet
@Composable
fun addSpaceContent(
    //Initialize the viewModel to invoke writes to the database
    spaceName: String, // Parameter to receive building name
    onSpaceNameChange: (String) -> Unit

) {


    val textColor = Color(0xFFD9D8DC)

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 8.dp)
            .fillMaxHeight(0.9f)

    ) {
        Text(
            "NAME"
        )

        Spacer(modifier = Modifier.padding(4.dp))

        MyTextField(
            text = spaceName,
            onTextChange = onSpaceNameChange,
            holding = "",
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            "BACKGROUND PHOTO"
        )

        Spacer(modifier = Modifier.padding(4.dp))

        ElevatedCard(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
            modifier = Modifier
                .height(310.dp)
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable { /*TODO*/ },
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp
                )

            ) {
                Text(
                    "Take a Photo",
                    modifier = Modifier
                        .padding(
                            top = 21.dp,
                            start = 18.dp
                        ),
                    fontSize = 17.sp

                )

            }

            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(0.8f)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /*TODO*/ }
                    .height(56.dp),
                shape = RectangleShape
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.End,

                    ) {
                    Text(
                        "Choose from Library",
                        modifier = Modifier
                            .weight(0.9f, fill = true)
                            .padding(
                                top = 13.dp,
                                start = 18.dp
                            ),
                        fontSize = 17.sp


                    )
                    Image(
                        painterResource(R.drawable.ic_round_navigate_next),
                        contentDescription = "Choose from Library"
                    )
                }


            }
        }


    }
}

@Composable
fun BottomSheetTopBar(
    onCancel: () -> Unit,
    onDone: () -> Unit,
    title: String
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CancelTextButton(onCancel)
        Text(title)
        DoneTextButton(onDone)
    }
}


//Imported function from TopAppBar
@Composable
fun CancelTextButton(
    onCancel : () -> Unit
) {
    TextButton(onClick = {onCancel() },
        modifier = Modifier
            .padding(8.dp)) {
        Text("Cancel")
    }
}

//Imported function from TopAppBar
@Composable
fun DoneTextButton(
    onDone: () -> Unit
) {
    TextButton(onClick = {onDone() },
        modifier = Modifier
            .padding(8.dp)) {
        Text("Done")
    }
}


//Imported function
@Composable
fun MyTextField(text: String, modifier: Modifier = Modifier, onTextChange: (String) -> Unit, holding: String) {
    OutlinedTextField(
        value = text,
        modifier = modifier,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(size = 8.dp),
        placeholder = { Text(holding) }
    )

}