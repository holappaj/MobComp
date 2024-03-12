package com.example.composetutorial

import SampleData
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composetutorial.database.UserDao
import com.example.composetutorial.database.UserRoomDatabase
import com.example.composetutorial.database.UserViewModel

data class UserMessage(val user: String, val body: String)

@Composable
fun Messages(onNavigateUserProfile: () -> Unit, viewModel: UserViewModel, applicationContext: Context) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        MessagesNavBar(onNavigateUserProfile)
        Conversation(SampleData.conversationSample, viewModel, applicationContext)
    }
}

@Composable
fun MessagesNavBar(onNavigateUserProfile: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = MaterialTheme.colorScheme.secondary),
        //contentAlignment = Alignment.CenterEnd

    ) {
        Button(
            onClick = onNavigateUserProfile,
            modifier = Modifier.width(100.dp),
        ) {
            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "User profile")
        }
    }
}
@Composable
fun Conversation(messages: List<UserMessage>, viewModel: UserViewModel, applicationContext: Context) {
/*    Button(
        onClick = viewUsers(),
        modifier = Modifier.width(300.dp)
        ) {
        Text(text = "View Users in Chat")
    }*/
    LazyColumn {
        items(messages) { message ->
            MessageCard(message, viewModel, applicationContext)
        }
    }
}

@Composable
fun MessageCard(msg: UserMessage, viewModel: UserViewModel, applicationContext: Context, modifier: Modifier = Modifier) {

    var currentUsername by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val currentUserId = 1

    LaunchedEffect(currentUserId) {
        var user = viewModel.getUserById(currentUserId)
        if (user != null) {
            currentUsername = user?.username!!
        }

        imageUri = Uri.fromFile(applicationContext.getFileStreamPath("user$currentUserId.jpg"))
    }

    Row(modifier = Modifier.padding(all = 8.dp)) {
        AsyncImage(
            model = imageUri,
            contentDescription = "",
            modifier = Modifier
                // Set image size to X dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                // Set border for the user picture
                .border(2.0.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        // Variable to keep track of if the message is extended or not
        var isExpanded by remember {
            mutableStateOf(false)
        }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            label = ""
        )

        // Toggle isExpanded variable when the Column is clicked
        Column (modifier = Modifier.clickable { isExpanded = !isExpanded}) {
            Text(
                text = currentUsername,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            // Add a vertical space between the user and their message
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                // surfaceColor will be gradually changing from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // Either display all the content or one line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

/*@
Preview not working
Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewConversation() {
    ComposeTutorialTheme {
        Conversation(SampleData.conversationSample)
    }
}
*/
