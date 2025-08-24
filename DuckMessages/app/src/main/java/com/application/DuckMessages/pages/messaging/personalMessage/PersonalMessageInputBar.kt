package com.application.DuckMessages.pages.messaging.personalMessage

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.application.DuckMessages.R

@Composable
fun PersonalMessageInputBar(
    modifier: Modifier = Modifier,
    onSendMessage: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 4.dp)
            .padding(horizontal = 12.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        // Text field for typing the message
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Message") },
            shape = CircleShape,

            colors = TextFieldDefaults.colors(
                // Set the background color
                unfocusedContainerColor = Color(red = 255, blue = 255, green = 255),
                focusedContainerColor = Color(red = 255, blue = 255, green = 255),

                // Remove the indicator line
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),

            singleLine = false,

//            leadingIcon = {
//                IconButton(onClick = { /*TODO: Emoji picker*/ }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.smiling_heart_icon),
//                        contentDescription = "Emoji"
//                    )
//                }
//            },
//
//            trailingIcon = {
//                Row {
//                    IconButton(onClick = { /*TODO: Attach file*/ }) {
//                        Icon(
//                            imageVector = Icons.Default.Add,
//                            contentDescription = "Attach"
//                        )
//                    }
//                    IconButton(onClick = { /*TODO: Camera*/ }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.camera_icon),
//                            contentDescription = "Camera"
//                        )
//                    }
//                }
//            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Send or Microphone button
        FloatingActionButton(
            onClick = {
                if (text.isNotBlank()) { // Prevent sending empty messages
                    onSendMessage(text)  // Call the passed-in function with the current text
                    text = ""            // Clear the text field after sending
                }
            },
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send message"
            )

//            // Icon changes based on whether text is empty
//            if (text.isEmpty()) {
//                Icon(
//                    painter = painterResource(R.drawable.mic_icon),
//                    contentDescription = "Record audio"
//                )
//            } else {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.Send,
//                    contentDescription = "Send message"
//                )
//            }
        }
    }
}