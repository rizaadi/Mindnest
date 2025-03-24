package com.zephysus.mindnest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zephysus.mindnest.core.database.dao.HabitDao
import com.zephysus.mindnest.core.database.model.HabitEntity
import com.zephysus.mindnest.core.database.util.InstantConverter

@Database(
    entities = [HabitEntity::class],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class
)
internal abstract class MindnestDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}