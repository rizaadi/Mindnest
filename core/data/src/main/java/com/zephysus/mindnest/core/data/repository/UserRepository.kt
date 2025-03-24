package com.zephysus.mindnest.core.data.repository

import com.zephysus.mindnest.core.model.data.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsersStream(): Flow<List<User>>

    fun getUserStream(userId: String): Flow<User?>

    suspend fun getUsers(): List<User>

    suspend fun getUser(userId: String): User?

    suspend fun createUser(user: User): String

    suspend fun updateUser(user: User)

    suspend fun deleteAllUsers()

    suspend fun deleteUser(userId: String)
}