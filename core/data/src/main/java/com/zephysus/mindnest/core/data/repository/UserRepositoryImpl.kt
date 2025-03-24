package com.zephysus.mindnest.core.data.repository

import com.zephysus.mindnest.core.common.Dispatcher
import com.zephysus.mindnest.core.common.MindnestDispatchers
import com.zephysus.mindnest.core.database.dao.UserDao
import com.zephysus.mindnest.core.database.model.UserEntity
import com.zephysus.mindnest.core.database.model.toEntity
import com.zephysus.mindnest.core.database.model.toDomain
import com.zephysus.mindnest.core.model.data.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    @Dispatcher(MindnestDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override fun getUsersStream(): Flow<List<User>> {
        return userDao.observeAll().map { it.mapNotNull(UserEntity::toDomain) }
    }

    override fun getUserStream(userId: String): Flow<User?> {
        return userDao.observeById(userId).map(UserEntity::toDomain)
    }

    override suspend fun getUsers(): List<User> {
        return userDao.getAll().ifEmpty {
            emptyList()
        }.map { it.toDomain()!! }
    }

    /**
     * Get a User with the given ID. Will return null if the User cannot be found.
     *
     * @param userId - The ID of the task
     */
    override suspend fun getUser(userId: String): User? {
        return userDao.getById(userId).toDomain()
    }

    override suspend fun createUser(user: User): String {
        val userId = withContext(ioDispatcher) {
            UUID.randomUUID().toString()
        }

        val newUser = User(
            id = userId,
            name = user.name,
            email = user.email,
            createdAt = user.createdAt,
        )

        userDao.upsert(newUser.toEntity())
        return userId
    }

    override suspend fun updateUser(user: User) {
        val newUser = getUser(user.id)?.copy(
            name = user.name,
            email = user.email,
            ) ?: throw Exception("User (id ${user.id} not found")

        userDao.upsert(newUser.toEntity())
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAll()
    }

    override suspend fun deleteUser(userId: String) {
        userDao.deleteById(userId)
    }
}