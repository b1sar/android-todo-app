package com.cebrailyilmaz.todoapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createTodo(todo: Todo)

    @Query("SELECT * FROM todos WHERE todos.id = :id")
    fun getTodo(id: Long): LiveData<Todo>

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("DELETE FROM todos WHERE id=:id")
    suspend fun deleteTodoById(id: Long)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("DELETE FROM todos")
    suspend fun deleteAll()
}
