package com.application.DuckMessages.pages.call

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.DuckMessages.Footer
import com.application.DuckMessages.viewModels.Call

@Composable
fun CallScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // TODO pull data and connect to lazy column with pagination feature
    val sampleCalls = listOf(
        Call("Alice", "11:30 AM"),
        Call("Bob", "10:15 AM"),
        Call("Charlie", "Yesterday, 12:00 AM"),
    )

    // -- VISUAL COMPONENT --
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            // HEADER
            // -- HEADER --
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 12.dp)
            ) {
                // Title and Action Icons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Duck Calls",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // RECENTS
            Box() {
                Text(
                    text = "Recent",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn() {
                items(sampleCalls) { call ->
                    CallItem(call = call)
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = Color.LightGray.copy(alpha = 0.4f)
                    )
                }
            }
        }

        Footer(
            navController = navController,
            currentPage = "calls"
        );
    }
}