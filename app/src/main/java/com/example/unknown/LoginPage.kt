package com.example.unknown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            OutlinedTextField(value = password, onValueChange = { password = it})
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
            onClick = { }) {
            Text(
                text = "Login"
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