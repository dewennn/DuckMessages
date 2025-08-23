package com.application.DuckMessages.pages.update

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.DuckMessages.Footer
import com.application.DuckMessages.R
import com.application.DuckMessages.viewModels.Chat

@Composable
fun FilterButton(modifier: Modifier = Modifier){
    Button(
        onClick = { /* ... */ },
        shape = CircleShape,
        border = BorderStroke(1.dp, Color.Gray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Gray
        )
    ) {
        Text("Test")
    }
}

@Composable
fun UpdateScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // TODO pull data and connect to lazy column with pagination feature
    val sampleChats = listOf(
        Chat("Alice", "Hey, how's it going?", "11:30 AM"),
        Chat("Bob", "Project update is attached.", "10:15 AM"),
        Chat("Charlie", "Lunch tomorrow?", "Yesterday"),
    )

    // State for the search bar text
    var searchText by remember { mutableStateOf("") }

    // State for the dropdown menu
    var showMenu by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
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
                        text = "Duck Updates",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = { /* TODO: Handle search click */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "Camera",
                            tint = Color.Black
                        )
                    }

                    Box {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More Options",
                                tint = Color.Black
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Status privacy") },
                                onClick = { /* Handle "Read all" click */ }
                            )
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = { /* Handle "Settings" click */ }
                            )
                        }
                    }
                }
            }

            // -- BODY --
            LazyColumn() {

            }
        }

        // -- FOOTER --
        Footer(
            currentPage = "updates",
            navController = navController
        )
    }
}