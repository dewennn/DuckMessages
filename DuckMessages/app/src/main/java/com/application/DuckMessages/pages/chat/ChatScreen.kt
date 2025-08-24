// In ui/screens/ChatScreen.kt

package com.application.DuckMessages.pages.chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.DuckMessages.R
import com.application.DuckMessages.viewModels.Chat
import com.application.DuckMessages.ui.components.ChatPreview


@Composable
fun ChatScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userId: String,
    displayName: String
) {
    // TODO pull data and connect to lazy column with pagination feature
    val sampleChats = listOf(
        Chat("Alice", "Hey, how's it going?", "11:30 AM"),
        Chat("Bob", "Project update is attached.", "10:15 AM"),
        Chat("Charlie", "Lunch tomorrow?", "Yesterday"),
    )

    // State for the UI
    var searchText by remember { mutableStateOf("") }
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
                        text = "Duck Messages",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.weight(1f))

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
                                text = { Text("Read all") },
                                onClick = { /* Handle "Read all" click */ }
                            )
                            DropdownMenuItem(
                                text = { Text("Add duck") },
                                onClick = { /* Handle "Settings" click */ }
                            )
                        }
                    }
                }
            }

            // -- BODY --
            LazyColumn() {
                // -- SEARCH BAR --
                item{
                    Text(
                        text = "Hi $displayName!",
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                item{
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // -- LIST OF CHATS --
                items(100) {
                    ChatPreview(
                        onClickFunc = { navController.navigate("personal_message") }
                    )
                }
            }
        }
    }
}