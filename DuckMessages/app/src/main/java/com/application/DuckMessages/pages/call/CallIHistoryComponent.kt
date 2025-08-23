package com.application.DuckMessages.pages.call

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
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
import com.application.DuckMessages.R
import com.application.DuckMessages.viewModels.Call

@Composable
fun CallItem(
    call: Call,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Profile Picture
        Image(
            // TODO change placeholder and connect to DB
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Name and Timestamp
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = call.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = call.timestamp,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Call Button
        IconButton(onClick = { /* TODO: Handle click */ }) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call Back",
                tint = Color.Black
            )
        }
    }
}