package com.example.composetutorial

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.database.UserViewModel

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "messages",
    viewModel: UserViewModel
) {
    val applicationContext = LocalContext.current
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("messages") {
            MessagesScreen(
                onNavigateUserProfile = { navController.navigate("userProfile") },viewModel, applicationContext
            )
        }
        composable("userProfile") {
            UserProfileScreen(
                onNavigateMessages = {
                    navController.navigate("messages") {
                        popUpTo("messages") {
                            inclusive = true
                        }
                    }
                }, viewModel, applicationContext
            )
        }
    }
}


@Composable
fun UserProfileScreen(
    onNavigateMessages: () -> Unit,
    viewModel: UserViewModel,
    applicationContext: Context,
) {
    userProfile(onNavigateMessages, viewModel, applicationContext)
}

@Composable
fun MessagesScreen(
    onNavigateUserProfile: () -> Unit,
    viewModel: UserViewModel,
    applicationContext: Context,
) {
    Messages(onNavigateUserProfile, viewModel, applicationContext)
}