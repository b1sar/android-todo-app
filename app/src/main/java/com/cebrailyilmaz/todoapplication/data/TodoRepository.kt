package com.cebrailyilmaz.todoapplication.data

import androidx.lifecycle.LiveData


interface TodoRepository {
    suspend fun fetchAllTodos(): LiveData<List<Todo>>

    suspend fun fetchTodo(id: Long): LiveData<Todo>

    suspend fun createTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun deleteTodoById(id: Long)

    suspend fun deleteAll()
}