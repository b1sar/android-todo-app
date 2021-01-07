package com.cebrailyilmaz.todoapplication.di


import com.cebrailyilmaz.todoapplication.data.TodoRepository
import com.cebrailyilmaz.todoapplication.data.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
abstract class TodoRepositoryModule {

    @Binds
    abstract fun provideTodoRepository(todorepository: TodoRepositoryImpl): TodoRepository
}