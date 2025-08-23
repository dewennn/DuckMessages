package com.application.DuckMessages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun FooterButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    isSelected: Boolean,
    onClickFunc: () -> Unit
) {
    val iconColor = Color.Black
    val iconBackground = if(isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
    val fontWeight = if(isSelected) FontWeight(1000) else FontWeight(500)

    Column(
        modifier = modifier.clickable{onClickFunc()},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        // -- ICON --
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(iconBackground)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = icon,
                contentDescription = text,
                tint = iconColor
            )
        }

        // -- TEXT --
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = fontWeight
        )
    }
}

@Composable
fun Footer(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    currentPage: String,
){
    // -- BORDER --
    HorizontalDivider(thickness = 1.dp, color = Color.LightGray, modifier = Modifier.alpha(0.5f))

    // -- BUTTONS --
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(top = 16.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
        ,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // CHATS
        FooterButton(
            modifier = Modifier.weight(1f),
            icon = painterResource(id = R.drawable.message_icon),
            text = "Chats",
            isSelected = currentPage == "chats",
            onClickFunc = { navController.navigate("chats") }
        )
        // UPDATES
        FooterButton(
            modifier = Modifier.weight(1f),
            icon = painterResource(id = R.drawable.update_icon),
            text = "Updates",
            isSelected = currentPage == "updates",
            onClickFunc = {  navController.navigate("updates") }
        )
        // CALLS
        FooterButton(
            modifier = Modifier.weight(1f),
            icon = painterResource(id = R.drawable.call_icon),
            text = "Calls",
            isSelected = currentPage == "calls",
            onClickFunc = {  navController.navigate("calls") }
        )
    }
}