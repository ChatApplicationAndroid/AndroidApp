package com.example.unknown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun checkLoginCredentials(username: String, password: String): Boolean {
    return username == "admin" && password == "admin"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        var username by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var loginError by remember { mutableStateOf(false) }
        var passwordHidden by rememberSaveable { mutableStateOf(true) }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Login Page",
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Icon(
            Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )

        Column {
            Text(text = "Username")
            OutlinedTextField(value = username, onValueChange = { username = it})
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Password")
            OutlinedTextField(
                value = password,
                onValueChange = { password = it},
                visualTransformation =
                if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordHidden = !passwordHidden }) {
                        val visibilityIcon =
                            if (passwordHidden) Icons.Default.Favorite
                            else Icons.Default.FavoriteBorder

                        val description = if (passwordHidden) "Show password" else "Hide password"

                        Icon(imageVector = visibilityIcon, contentDescription = description)
                    }
                },
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
            onClick = {
                val isValidLogin = checkLoginCredentials(username, password)
                if (isValidLogin) {
                    // Navigate to the next screen on successful login
                    navController.navigate("message_page"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                } else {
                    // Display an error message or handle the error as needed
                    loginError = true
                    // Clear the username and password fields on incorrect login
                    username = ""
                    password = ""
                }
            }) {
            Text(
                text = "Login"
            )
        }
        if (loginError) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside of it
                    loginError = false
                },
                title = {
                    Text(text = "Login Failed")
                },
                text = {
                    Text(text = "Invalid username or password. Please try again.")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            // Dismiss the dialog when the "OK" button is clicked
                            loginError = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
            onClick = {
            navController.navigate("register_page"){
            popUpTo(navController.graph.startDestinationId)
            launchSingleTop = true
        } }) {
            Text(
                text = "Create An Account"
            )
        }

        Button(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
            onClick = {
            navController.navigate("reset_page"){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            } }) {
            Text(
                text = "Reset Password"
            )
        }

    }
}