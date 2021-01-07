package com.cebrailyilmaz.todoapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImpl @Inject constructor(val todoDao: TodoDao) : TodoRepository {

    override suspend fun fetchAllTodos(): LiveData<List<Todo>> {
        return todoDao.getAllTodos()
    }

    override suspend fun fetchTodo(id: Long): LiveData<Todo> {
        return todoDao.getTodo(id)
    }

    override suspend fun createTodo(todo: Todo) {
        return todoDao.createTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        return todoDao.updateTodo(todo)
    }

    override suspend fun deleteTodoById(id: Long) {
        return todoDao.deleteTodoById(id)
    }

    override suspend fun deleteAll() {
        todoDao.deleteAll()
    }
}