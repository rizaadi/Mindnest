package com.zephysus.mindnest.core.data.di

import com.zephysus.mindnest.core.data.repository.HabitRepository
import com.zephysus.mindnest.core.data.repository.HabitRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindBookRepository(repository: HabitRepositoryImpl): HabitRepository
}