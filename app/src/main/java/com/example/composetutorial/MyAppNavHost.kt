package com.example.composetutorial

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController



@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "messages"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("messages") {
            MessagesScreen(
                onNavigateUserProfile = { navController.navigate("userProfile") },
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
                }
            )
        }
    }
}


@Composable
fun UserProfileScreen(
    onNavigateMessages: () -> Unit,
) {
    UserProfile(onNavigateMessages)
}

@Composable
fun MessagesScreen(
    onNavigateUserProfile: () -> Unit,
) {
    Messages(onNavigateUserProfile)
}