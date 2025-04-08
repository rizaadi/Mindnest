package com.zephysus.mindnest.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zephysus.mindnest.core.database.dao.HabitDao
import com.zephysus.mindnest.core.database.dao.UserDao
import com.zephysus.mindnest.core.database.model.HabitEntity
import com.zephysus.mindnest.core.database.model.UserEntity
import com.zephysus.mindnest.core.database.util.InstantConverter

@Database(
    entities = [UserEntity::class, HabitEntity::class],
    version = 1,
    autoMigrations = [],
    exportSchema = true,
)
@TypeConverters(
    InstantConverter::class
)
internal abstract class MindnestDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao
}