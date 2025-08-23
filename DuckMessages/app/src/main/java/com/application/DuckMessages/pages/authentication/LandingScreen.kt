package com.application.DuckMessages.pages.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.DuckMessages.R
import androidx.navigation.NavHostController

// Main Composable for the Landing Screen
@Composable
fun LandingScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    // Use a Surface for background color and theme application
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5) // A light grey background
    ) {
        // Column to arrange elements vertically
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Spacer to push content from the top
            Spacer(modifier = Modifier.height(64.dp))

            // App Logo
            // Replace R.drawable.ic_duck_logo with your actual logo resource
            // For preview purposes, a placeholder might be needed.
            Image(
                painter = painterResource(id = R.drawable.duck_icon),
                contentDescription = "Duck Messages Logo",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // App Name
            Text(
                text = "Duck Messages",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333)
            )

            // App Tagline
            Text(
                text = "Simple, secure, and fun messaging.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            // This spacer pushes the buttons to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // Action Buttons Section
            ActionButtons(
                onLoginClicked = { navController.navigate("login") },
                onRegisterClicked = { navController.navigate("register") }
            )
        }
    }
}

@Composable
fun ActionButtons(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    // Row to arrange buttons horizontally
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Login Button
        Button(
            onClick = onLoginClicked,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(4.dp), // Rounded corners for the button
            border = BorderStroke(1.dp, Color(0xFF4A90E2).copy(alpha = 0.4f)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color(0xFF4A90E2)
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        ) {
            Text(text = "Login", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        // Register Button
        Button(
            onClick = onRegisterClicked,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4A90E2),
                contentColor = MaterialTheme.colorScheme.primary
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        ) {
            Text(text = "Register", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}