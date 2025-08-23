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

@Composable
fun PersonalMessageScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    // TODO update this variable to use values from the remote API
    val name = "Duck Doe"
    val profilePictureUrl = ""

    // TODO pull data and connect to lazy column with pagination feature
    val messages = listOf(
        Message("Hey, how's it going?", "8:18 PM", true),
        Message("Pretty good! Just working on this Compose stuff.", "8:19 PM", false, isRead = true),
        Message("Nice! Is it hard?", "8:19 PM", true),
        Message("A bit at first, but it's really powerful once you get the hang of it.", "8:20 PM", false, isRead = true),
        Message("You should try it.", "8:20 PM", false, isRead = true),
        Message("I will!", "8:21 PM", true)
    )

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
                    text = "Unknown user",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(onClick = { /* TODO: Handle camera click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon),
                        contentDescription = "Camera",
                        tint = Color.Black
                    )
                }

                IconButton(onClick = { /* TODO: Handle call click */ }) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Voice Call",
                        tint = Color.Black
                    )
                }

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
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
            ,
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                if (message.isIncoming) {
                    IncomingMessageBubble(
                        text = message.text,
                        timestamp = message.timestamp
                    )
                } else {
                    SentMessageBubble(
                        text = message.text,
                        timestamp = message.timestamp,
                        isRead = message.isRead
                    )
                }
            }
        }

        // -- INPUT --
        PersonalMessageInputBar()
    }
}