package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetutorial.database.User
import com.example.composetutorial.database.UserRepository
import com.example.composetutorial.database.UserRoomDatabase
import com.example.composetutorial.database.UserViewModel
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val applicationScope = CoroutineScope(SupervisorJob())
        val database by lazy { UserRoomDatabase.getDatabase(this, applicationScope) }
        val repository by lazy { UserRepository(database.userDao())}
        val viewModel = UserViewModel(database.userDao())
        viewModel.insertUserIfNonexistant(User(id=1, username = "holappaj"))
        setContent {
            ComposeTutorialTheme {
                MyAppNavHost(viewModel=viewModel)
            }
        }
    }
}