package com.zephysus.mindnest.core.data.repository

import com.zephysus.mindnest.core.model.data.Habit
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    fun getHabitsStream(): Flow<List<Habit>>

    fun getHabitStream(habitId: String): Flow<Habit?>

    suspend fun getHabits(): List<Habit>

    suspend fun getHabit(habitId: String): Habit?

    suspend fun createHabit(habit: Habit): String

    suspend fun updateHabit(habit: Habit)

    suspend fun deleteAllHabits()

    suspend fun deleteHabit(habitId: String)
}