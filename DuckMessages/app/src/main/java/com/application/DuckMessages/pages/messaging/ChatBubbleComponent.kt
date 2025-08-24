package com.application.DuckMessages.pages.messaging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SentMessageBubble(
    text: String,
    timestamp: String,
    isRead: Boolean // New parameter for read status
) {
    // -- ROW CONTAINER --
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),

        horizontalArrangement = Arrangement.End
    ) {

        // -- MESSAGE BUBBLE --
        Box(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 280.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 4.dp)
                .padding(horizontal = 12.dp)
//                .clip(SentBubbleShape())
//                .padding(start = 12.dp, end = 24.dp)
        ) {
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.widthIn(min = 100.dp)) {
                Text(
                    text = text,
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = timestamp,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Read status",
                        modifier = Modifier.size(16.dp),
                        tint = if (isRead) Color(0xFF00B2FF) else Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun IncomingMessageBubble(
    text: String,
    timestamp: String // New parameter for timestamp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),

        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .widthIn(min = 100.dp, max = 280.dp)
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 8.dp)
                .padding(horizontal = 12.dp)
//                .clip(IncomingBubbleShape())
//                .padding(start = 24.dp, end = 12.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.widthIn(min = 100.dp)) {
                Text(text = text, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = timestamp,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}