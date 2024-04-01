package com.homey.homeysystemsappv10.NavScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.homey.homeysystemsappv10.NavRoutes

@Composable
fun LogInScreen(navController: NavHostController) {
    ConstraintLayout(
        Modifier
            .padding(16.dp)
    ) {
        val (NameText,
            PassText,
            LogInButton,
            AccountText,
            SignUpText) = createRefs()

        var NametextState by rememberSaveable {
            mutableStateOf("")
        }
        val NameonTextChange = { text: String ->
            NametextState = text
        }
        var PasstextState by rememberSaveable { mutableStateOf("") }
        var PassonTextChange = {text: String -> PasstextState = text}


        val TextFieldMod = Modifier
            .height(56.dp)
            .background(color = Color.White)
            .fillMaxWidth()




        // Add the name Text
        MyTextField(
            text = NametextState,
            onTextChange = NameonTextChange,
            modifier = Modifier
                .constrainAs(NameText) {

                    centerHorizontallyTo(parent)
                    linkTo(parent.top, parent.bottom, bias = 0.44f)
                }.then(TextFieldMod),
            holding = "Email"

        )

        //Add the password field
        MyTextField(
            text = PasstextState,
            onTextChange = PassonTextChange,
            modifier = Modifier
                .constrainAs(PassText) {

                    centerHorizontallyTo(parent)
                    top.linkTo(NameText.bottom, margin = 8.dp)
                }.then(TextFieldMod),
            holding = "Password"
        )

        //LogIn Button
        Button(
            onClick = {navController.navigate(NavRoutes.HomeScreen.route)},
            modifier = Modifier
                .constrainAs(LogInButton){
                    centerHorizontallyTo(parent)
                    top.linkTo(PassText.bottom, margin = 8.dp)
                },
            shape = RoundedCornerShape(size = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            )
            //Check on how to input colors on a button
        ){
            Text("Log In")
        }

        //Text
        Text(
            text = "Don't have an Account?",
            fontSize = 13.sp,
            modifier = Modifier
                .constrainAs(AccountText){
                    top.linkTo(LogInButton.bottom, margin = 24.dp)
                    centerHorizontallyTo(parent)

                }
        )

        //Sign Up text
        TextButton(
            onClick = {/*Do Something*/},
            modifier = Modifier
                .constrainAs(SignUpText){
                    top.linkTo(AccountText.bottom, margin = 0.dp)
                    centerHorizontallyTo(parent)

                }
        ){
            Text(text = "Sign Up",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 0.dp))

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
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






