package com.example.composetutorial.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.composetutorial.database.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserIfNonexistant(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id=:id")
    fun getUserById(id: Int): User

    @Query("SELECT * FROM user WHERE username=:username")
    fun getUserByUsername(username: String): User
}