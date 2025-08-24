// In ui/screens/ChatScreen.kt

package com.application.DuckMessages.pages.chat

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.application.DuckMessages.R
import com.application.DuckMessages.network.viewmodel.AddFriendState
import com.application.DuckMessages.network.viewmodel.FriendListState
import com.application.DuckMessages.network.viewmodel.FriendViewModel
import com.application.DuckMessages.viewModels.Chat
import com.application.DuckMessages.ui.components.ChatPreview
import com.google.android.material.progressindicator.CircularProgressIndicator


@Composable
fun ChatScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userId: String,
    displayName: String,
    friendViewModel: FriendViewModel // Accept the ViewModel as a parameter
) {
    val context = LocalContext.current

    var showMenu by remember { mutableStateOf(false) }
    var showAddDuckDialog by remember { mutableStateOf(false) }
    var duckPhoneNumber by remember { mutableStateOf("") }


    // Observe the state from the ViewModel
    val friendListState by friendViewModel.friendListState.collectAsState()
    val addFriendState by friendViewModel.addFriendState.collectAsState()

    // Trigger the data fetch when the screen is first composed
    LaunchedEffect(key1 = true) {
        friendViewModel.fetchFriends(userId)
    }

    LaunchedEffect(addFriendState) {
        when (val state = addFriendState) {
            is AddFriendState.Success -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                showAddDuckDialog = false
                duckPhoneNumber = ""
                friendViewModel.resetAddFriendState()
            }
            is AddFriendState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                friendViewModel.resetAddFriendState()
            }
            else -> Unit
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        // -- HEADER --
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 12.dp)
                .padding(horizontal = 12.dp)
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
                            text = { Text("Add Duck") },
                            onClick = {
                                showMenu = false
                                showAddDuckDialog = true
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Log Out") },
                            onClick = {
                                navController.navigate("login")
                            }
                        )
                    }
                }
            }
        }

        // -- BODY --
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center // Center loading/error messages
        ) {
            when (val state = friendListState) {
                is FriendListState.Loading -> {
                    CircularProgressIndicator()
                }
                is FriendListState.Error -> {
                    Text(text = state.message)
                }
                is FriendListState.Success -> {
                    // When data is successfully loaded, show the LazyColumn
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp)
                    ) {
                        item {
                            Text(
                                text = "Hi $displayName, Message other ducks below!",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        // Use the real friend list from the state
                        items(items = state.friends) { friend ->
                            val lastMessage = friend.messagePreview ?: ""
                            val timestamp = friend.timestamp ?: ""

                            ChatPreview(
                                chat = Chat(name = friend.displayName, lastMessage = lastMessage, timestamp = timestamp),
                                onClickFunc = { navController.navigate("personal_message/${userId}/${friend.friendId}/${friend.displayName}") }
                            )
                        }
                    }
                }
                is FriendListState.Idle -> {
                    // You can show a loading indicator here as well, as it will quickly change
                }
            }
        }

        if (showAddDuckDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Close the dialog when the user clicks outside of it
                    showAddDuckDialog = false
                },
                title = {
                    Text(text = "Add a New Duck")
                },
                text = {
                    // The content of the dialog, e.g., an input field
                    OutlinedTextField(
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black
                        ),
                        value = duckPhoneNumber,
                        onValueChange = { duckPhoneNumber = it },
                        label = { Text("Enter Duck's Phone Number") },
                        isError = addFriendState is AddFriendState.Error
                    )
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                        onClick = {
                            if (duckPhoneNumber.isNotBlank()) {
                                friendViewModel.addFriend(
                                    senderId = userId,
                                    receiverPhoneNumber = duckPhoneNumber
                                )
                            }
                        },
                        enabled = addFriendState != AddFriendState.Loading
                    ) {
                        if (addFriendState == AddFriendState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White
                            )
                        } else {
                            Text("Add", color = Color.White)
                        }
                    }
                },
                dismissButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                        onClick = { showAddDuckDialog = false }
                    ) { Text("Cancel", color = Color.White) }
                }

            )
        }
    }
}