package com.zephysus.mindnest.core.data.di

import com.zephysus.mindnest.core.data.repository.HabitRepository
import com.zephysus.mindnest.core.data.repository.HabitRepositoryImpl
import com.zephysus.mindnest.core.data.repository.UserRepository
import com.zephysus.mindnest.core.data.repository.UserRepositoryImpl
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
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindHabitRepository(repository: HabitRepositoryImpl): HabitRepository
}