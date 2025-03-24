package com.zephysus.mindnest.core.database.di

import android.content.Context
import androidx.room.Room
import com.zephysus.mindnest.core.database.MindnestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesMindnestDatabase(
        @ApplicationContext context: Context,
    ): MindnestDatabase = Room.databaseBuilder(
        context,
        MindnestDatabase::class.java,
        "mindnest-database",
    ).build()
}
