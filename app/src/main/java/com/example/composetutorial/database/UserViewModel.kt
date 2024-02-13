package com.example.composetutorial.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao: UserDao) : ViewModel() {
/*
    fun insertUser(user: User) = viewModelScope.launch{
        if (getUserById(user.id) == null) {
            userDao.insertUser2(user)
        }
    }
*/
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    fun insertUserIfNonexistant(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUserIfNonexistant(user)
        }
    }

    suspend fun getUserById(userId: Int): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserById(userId)
        }
    }

    fun updateUsername(userId: Int, newUsername: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var user = getUserById(userId)
            if (user != null) {
                user.username = newUsername
                userDao.updateUser(user)
            }
        }
    }

    fun getUserById2(userId: Int) = viewModelScope.launch {
        userDao.getUserById(userId)
    }

    fun getUserByUsername(username: String) = viewModelScope.launch {
        userDao.getUserByUsername(username)
    }
}
