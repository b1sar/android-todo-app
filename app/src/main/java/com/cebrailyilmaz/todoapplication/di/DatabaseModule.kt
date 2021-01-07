package com.cebrailyilmaz.todoapplication.di

import android.content.Context
import androidx.room.Room
import com.cebrailyilmaz.todoapplication.data.AppDatabase
import com.cebrailyilmaz.todoapplication.data.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideLogDao(database: AppDatabase): TodoDao {
        return database.logDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todos.db"
        ).build()
    }
}