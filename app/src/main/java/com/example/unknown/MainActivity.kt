package com.example.unknown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unknown.ui.theme.UnknownTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnknownTheme {
                NavigationApp()
            }
        }
    }
}

@Composable
fun NavigationApp(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = { LoginPage(navController = navController) })
        composable("register_page", content = { RegisterPage(navController = navController) })
        composable("reset_page", content = { ResetPage(navController = navController) })
        composable("message_page", content = {MessagePage(navController = navController)})
        composable("sample_chat_page", content = { ChatPage(navController = navController,
            "0") })
    })
}


