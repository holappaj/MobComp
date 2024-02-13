package com.example.composetutorial.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (private val userDao: UserDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserWithId(id: Int): User? {
        return userDao.getUserById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getUserWithUsername(username: String): User {
        return userDao.getUserByUsername(username)
    }
}

