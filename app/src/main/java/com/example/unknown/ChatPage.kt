package com.example.unknown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class Message(val id: String, val text: String, val isSentByUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPage(
    navController: NavController,
    conversationId: String // Pass the conversation ID as a parameter
) {
    var newMessage by remember { mutableStateOf("") }

    // Sample list of chat messages
    val chatMessages = mutableListOf(
        Message(id = "1", text = "Hello!", isSentByUser = false),
        Message(id = "2", text = "Hi there!", isSentByUser = true),
        Message(id = "3", text = "How's it going?", isSentByUser = false),
        Message(id = "4", text = "I'm doing well, thanks!", isSentByUser = true),
        // Add more chat messages as needed
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        TopAppBar(
            title = { Text(text = "Chat Page") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        // Navigate back to the messaging page
                        navController.popBackStack()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(chatMessages) { message ->
                ChatMessage(message = message
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = newMessage,
                onValueChange = { newMessage = it },
                modifier = Modifier
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            )

            IconButton(
                onClick = {
                    // Send the new message
                    if (newMessage.isNotBlank()) {
                        val message = Message(
                            id = chatMessages.size.toString(),
                            text = newMessage,
                            isSentByUser = true
                        )
                        chatMessages.add(Message(id = "10", text = newMessage, isSentByUser = true))
                        newMessage = ""

                        // You can send the message to the server here if needed

                        // Scroll to the latest message
                        // ...
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}

@Composable
fun ChatMessage(message: Message) {
    val backgroundColor = if (message.isSentByUser) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor)
            .padding(8.dp)
        ,
        contentAlignment = if (!message.isSentByUser) Alignment.TopStart else Alignment.BottomEnd,
    ) {
        Text(
            text = message.text,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}
