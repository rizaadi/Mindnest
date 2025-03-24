package com.zephysus.mindnest.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zephysus.mindnest.core.model.data.Habit
import kotlinx.datetime.Instant

@Entity(
    tableName = "habit",
)
data class HabitEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val frequency: String,
    @ColumnInfo(defaultValue = "0")
    val streak: Int,
    @ColumnInfo(name = "last_completed")
    val lastCompleted: Instant,
    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
)

fun HabitEntity?.toDomain(): Habit? {
    return this?.let {
        Habit(
            id = id,
            name = name,
            streak = streak,
            frequency = frequency,
            lastCompleted = lastCompleted,
            createdAt = createdAt,
        )
    }
}

fun Habit.toEntity() = HabitEntity(
    id = id!!,
    name = name,
    streak = streak,
    frequency = frequency,
    lastCompleted = lastCompleted,
    createdAt = createdAt,
)

