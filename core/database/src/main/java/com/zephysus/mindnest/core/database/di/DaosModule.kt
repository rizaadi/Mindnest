package com.zephysus.mindnest.core.database.di

import com.zephysus.mindnest.core.database.MindnestDatabase
import com.zephysus.mindnest.core.database.dao.HabitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesHabitsDao(
        database: MindnestDatabase,
    ): HabitDao = database.habitDao()
}