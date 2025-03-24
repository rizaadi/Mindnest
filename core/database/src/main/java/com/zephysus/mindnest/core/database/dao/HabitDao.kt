package com.zephysus.mindnest.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.zephysus.mindnest.core.database.model.HabitEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [HabitEntity] access
 */
@Dao
interface HabitDao {
    /**
     * Observes serves list of habits.
     *
     * @return all habits.
     */
    @Query("SELECT * FROM habit")
    fun observeAll(): Flow<List<HabitEntity>>

    /**
     * Observes a single habit.
     *
     * @param habitId the habit id.
     * @return the habit with habitId.
     */
    @Query("SELECT * FROM habit WHERE id = :habitId")
    fun observeById(habitId: String): Flow<HabitEntity>

    /**
     * Select all habits from the habits table.
     *
     * @return all habits.
     */
    @Query("SELECT * FROM habit")
    suspend fun getAll(): List<HabitEntity>

    /**
     * Select a habit by id.
     *
     * @param habitId the habit id.
     * @return the habit with habitId.
     */
    @Query("SELECT * FROM habit WHERE id = :habitId")
    suspend fun getById(habitId: String): HabitEntity

    /**
     * Insert or update habit in the database. If a task already exists, replace it.
     *
     * @param habit the habit to be inserted or updated.
     */
    @Upsert
    suspend fun upsert(habit: HabitEntity)

    /**
     * Insert or update habits in the database. If a task already exists, replace it.
     *
     * @param habits the habits to be inserted or updated.
     */
    @Upsert
    suspend fun upsertAll(habits: List<HabitEntity>)

    /**
     * Delete a habit by id.
     *
     * @return the number of habits deleted. This should always be 1.
     */
    @Query("DELETE FROM habit WHERE id = :habitId")
    suspend fun deleteById(habitId: String): Int

    /**
     * Delete all habits.
     */
    @Query("DELETE FROM habit")
    suspend fun deleteAll()
}