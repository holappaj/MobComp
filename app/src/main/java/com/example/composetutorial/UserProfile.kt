package com.example.composetutorial

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

@Composable
fun UserProfile(onNavigateMessages: () -> Unit) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        UserProfileNavBar(onNavigateMessages)
        UserProfileCard()
    }
}

@Composable
fun UserProfileNavBar(onNavigateMessages: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {
        Button(
            onClick = onNavigateMessages,
            modifier = Modifier.width(100.dp),
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to messages")
        }
    }

}

@Composable
fun UserProfileCard(modifier: Modifier = Modifier) {    // Add padding around our
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.mob_comp_hw1),
            contentDescription = "User Image",
            modifier = Modifier
                // Set image size to X dp
                .size(100.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                // Set border for the user picture
                .border(4.0.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column() {
            Text(
                text = "Username:",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "holappaj",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
fun PreviewUserProfile() {
    ComposeTutorialTheme {
        UserProfileCard()
    }
}