package com.example.composetutorial

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composetutorial.database.UserViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker


@Composable
fun userProfile(onNavigateMessages: () -> Unit, viewModel: UserViewModel, applicationContext: Context) {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        UserProfileNavBar(onNavigateMessages, applicationContext)
        UserProfileCard(viewModel, applicationContext = applicationContext)
    }
}

@Composable
fun UserProfileNavBar(onNavigateMessages: () -> Unit, applicationContext: Context) {
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
fun UserProfileCard(viewModel: UserViewModel, modifier: Modifier = Modifier, applicationContext: Context) {

    fun saveImage(imageUri: Uri, currentUserId: Int) {
        val resolver = applicationContext.contentResolver
        var imageName = "user$currentUserId.jpg"
        resolver.openInputStream(imageUri)?.use { input ->
            applicationContext.openFileOutput(imageName, Context.MODE_PRIVATE)?.use { output ->
             input.copyTo(output)
            }
        }
    }
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

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            imageUri = uri
            saveImage(imageUri!!, currentUserId)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    fun changeProfilePicture() {
        launcher.launch((PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)))
    }

    Row {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            AsyncImage(
                model = imageUri,
                contentDescription = "User image",
                modifier = Modifier
                    // Set image size to X dp
                    .size(100.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    // Set border for the user picture
                    .border(4.0.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clickable { changeProfilePicture() }
            )
            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column() {
            Text(
                text = "Username:",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = currentUsername,
                onValueChange = {
                    currentUsername = it
                },
                modifier = Modifier
                    .padding(20.0.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.updateUsername(currentUserId, currentUsername)
                }
            )
            {
                Text(text = "Update name")
            }
        }
    }
}
/*
@Preview
@Composable
fun PreviewUserProfile() {
    ComposeTutorialTheme {
        UserProfileCard(User(username = "testi testaaja"), applicationContext = LocalContext.current)
    }
}
*/
