package com.application.DuckMessages.pages.messaging.personalMessage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.DuckMessages.R
import com.application.DuckMessages.pages.messaging.IncomingMessageBubble
import com.application.DuckMessages.pages.messaging.SentMessageBubble
import com.application.DuckMessages.viewModels.Message
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.application.DuckMessages.network.viewmodel.MessageState
import com.application.DuckMessages.network.viewmodel.MessageViewModel

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun PersonalMessageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    senderId: String = "",
    receiverId: String = "",
    displayName: String = "",
    messageViewModel: MessageViewModel
){
    val messageState by messageViewModel.messageState.collectAsState()

    LaunchedEffect(key1 = senderId, key2 = receiverId) {
        messageViewModel.fetchMessages(senderId = senderId, receiverId = receiverId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(red = 236, green = 229, blue = 221))
    ) {
        // -- HEADER --
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 16.dp, bottom = 12.dp)
                .padding(horizontal = 12.dp)
        ) {
            // Title and Action Icons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Profile Picture
                Image(
                    // TODO change placeholder and connect to DB
                    painter = painterResource(id = R.drawable.duck_icon),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = displayName,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /* TODO: Handle more options click */ }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More Options",
                        tint = Color.Black
                    )
                }
            }
        }

        // -- BODY --
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            when (val state = messageState) {
                is MessageState.Loading -> {
                    CircularProgressIndicator()
                }
                is MessageState.Error -> {
                    Text(text = state.message)
                }
                is MessageState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        reverseLayout = true
                    ) {
                        items(state.messages) { message ->
                            // Your API's 'isSender' tells you if YOU sent the message
                            if (message.isSender) {
                                SentMessageBubble(
                                    text = message.content ?: "test",
                                    timestamp = message.sentAt ?: "",
                                    isRead = message.isRead
                                )
                            } else {
                                // If you are not the sender, it's an incoming message
                                IncomingMessageBubble(
                                    text = message.content ?: "test",
                                    timestamp = message.sentAt ?: ""
                                )
                            }
                        }
                    }
                }
                is MessageState.Idle -> {
                    // Idle state, you can show a loading indicator or nothing
                }
            }
        }

        // -- INPUT --
        PersonalMessageInputBar(
            onSendMessage = { messageContent ->
                messageViewModel.sendMessage(
                    senderId = senderId,
                    receiverId = receiverId,
                    content = messageContent
                )
            }
        )
    }
}