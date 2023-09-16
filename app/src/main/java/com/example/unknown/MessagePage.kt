package com.example.unknown

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Conversation(val id: String, val name: String, val lastMessage: String, val distance: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagePage(navController: NavController) {

    // Define a list of sample conversations
    val conversations = listOf(
        Conversation(id = "1", name = "Alice", lastMessage = "Hey there!", distance = 12),
        Conversation(id = "2", name = "Bob", lastMessage = "What's up?", distance = 7),
        Conversation(id = "3", name = "Charlie", lastMessage = "How's it going?", distance = 2),
        Conversation(id = "4", name = "David", lastMessage = "Long time no see!", distance = 3),
        Conversation(id = "5", name = "Eve", lastMessage = "Hello!", distance = 1),
        Conversation(id = "6", name = "Frank", lastMessage = "Hi, how's your day?", distance = 9),
        Conversation(id = "7", name = "Grace", lastMessage = "Hey, what's new?", distance = 4),
        Conversation(id = "8", name = "Hannah", lastMessage = "Good morning!", distance = 0),
        Conversation(id = "9", name = "Ivy", lastMessage = "Hello there!", distance = 17),
        Conversation(id = "10", name = "Jack", lastMessage = "Hi, nice to meet you!", distance = 21),
        // More like these
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        TopAppBar(
            title = { Text(text = "Messaging Page") },
            actions = {
                IconButton(
                    onClick = {
                        // Implement action for composing a new message here
                        // For example, you can navigate to a new message screen
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Compose")
                }
            }
        )

        LazyColumn {
            items(conversations) { conversation ->
                ConversationItem(conversation = conversation, onClick = {
                    // Navigate to the chat screen for this conversation
                    // navController.navigate("chat/${conversation.id}")
                    // navigating to sample chat page
                    navController.navigate("sample_chat_page"){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                })
            }
        }
    }
}

@Composable
fun ConversationItem(conversation: Conversation, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "${conversation.name} (${conversation.distance} pts away) " , fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = conversation.lastMessage, fontSize = 16.sp)
        }
    }
}
