package com.zephysus.mindnest.core.data.repository

import com.zephysus.mindnest.core.common.Dispatcher
import com.zephysus.mindnest.core.common.MindnestDispatchers
import com.zephysus.mindnest.core.database.dao.HabitDao
import com.zephysus.mindnest.core.database.model.HabitEntity
import com.zephysus.mindnest.core.database.model.toEntity
import com.zephysus.mindnest.core.database.model.toDomain
import com.zephysus.mindnest.core.model.data.Habit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao,
    @Dispatcher(MindnestDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : HabitRepository {
    override fun getHabitsStream(): Flow<List<Habit>> {
        return habitDao.observeAll().map { it.mapNotNull(HabitEntity::toDomain) }
    }

    override fun getHabitStream(habitId: String): Flow<Habit?> {
        return habitDao.observeById(habitId).map(HabitEntity::toDomain)
    }

    override suspend fun getHabits(): List<Habit> {
        return habitDao.getAll().ifEmpty {
            emptyList()
        }.map { it.toDomain()!! }
    }

    /**
     * Get a Habit with the given ID. Will return null if the Habit cannot be found.
     *
     * @param habitId - The ID of the task
     */
    override suspend fun getHabit(habitId: String): Habit? {
        return habitDao.getById(habitId).toDomain()
    }

    override suspend fun createHabit(habit: Habit): String {
        val habitId = withContext(ioDispatcher) {
            UUID.randomUUID().toString()
        }

        val newHabit = Habit(
            id = habitId,
            name = habit.name,
            frequency = habit.frequency,
            streak = habit.streak,
            lastCompleted = habit.lastCompleted,
            createdAt = habit.createdAt,
        )

        habitDao.upsert(newHabit.toEntity())
        return habitId
    }

    override suspend fun updateHabit(habit: Habit) {
        val newHabit = getHabit(habit.id!!)?.copy(
            name = habit.name,
            frequency = habit.frequency,
            streak = habit.streak,
            lastCompleted = habit.lastCompleted,
        ) ?: throw Exception("Book (id ${habit.id} not found")

        habitDao.upsert(newHabit.toEntity())
    }

    override suspend fun deleteAllHabits() {
        habitDao.deleteAll()
    }

    override suspend fun deleteHabit(habitId: String) {
        habitDao.deleteById(habitId)
    }
}