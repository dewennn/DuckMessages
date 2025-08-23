// In a new file, e.g., ChatPreviewComponent.kt
package com.application.DuckMessages.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.application.DuckMessages.R
import com.application.DuckMessages.viewModels.Chat

@Composable
fun ChatPreview(
    modifier: Modifier = Modifier,
    onClickFunc: () -> Unit,
    chat: Chat = Chat(
        "Duck Doe",
        "See you tomorrow!",
        "10:45 AM"
    ),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable{onClickFunc()}
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
            )
    ) {
        // Profile Picture
        Image(
            // TODO change placeholder and connect to DB
            painter = painterResource(id = R.drawable.duck_icon),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Name and Last Message
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = chat.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = chat.lastMessage,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Timestamp
        Text(
            text = chat.timestamp,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}