package com.zephysus.mindnest.core.database.dao


import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.zephysus.mindnest.core.database.model.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [UserEntity] access
 */
@Dao
interface UserDao {
    /**
     * Observes serves list of users.
     *
     * @return all users.
     */
    @Query("SELECT * FROM user")
    fun observeAll(): Flow<List<UserEntity>>

    /**
     * Observes a single user.
     *
     * @param userId the user id.
     * @return the user with userId.
     */
    @Query("SELECT * FROM user WHERE id = :userId")
    fun observeById(userId: String): Flow<UserEntity>

    /**
     * Select all users from the users table.
     *
     * @return all users.
     */
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    /**
     * Select a user by id.
     *
     * @param userId the user id.
     * @return the user with userId.
     */
    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getById(userId: String): UserEntity

    /**
     * Insert or update user in the database. If a task already exists, replace it.
     *
     * @param user the user to be inserted or updated.
     */
    @Upsert
    suspend fun upsert(user: UserEntity)

    /**
     * Insert or update users in the database. If a task already exists, replace it.
     *
     * @param users the users to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAll(users: List<UserEntity>)

    /**
     * Delete a user by id.
     *
     * @return the number of users deleted. This should always be 1.
     */
    @Query("DELETE FROM user WHERE id = :userId")
    suspend fun deleteById(userId: String): Int

    /**
     * Delete all users.
     */
    @Query("DELETE FROM user")
    suspend fun deleteAll()
}