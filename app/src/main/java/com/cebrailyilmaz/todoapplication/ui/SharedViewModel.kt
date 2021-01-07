package com.cebrailyilmaz.todoapplication.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.cebrailyilmaz.todoapplication.data.Todo
import com.cebrailyilmaz.todoapplication.data.TodoRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class SharedViewModel @ViewModelInject constructor(val todoRepo: TodoRepository) : ViewModel() {
    val todos: LiveData<List<Todo>> = liveData { emitSource(todoRepo.fetchAllTodos()) }

    val currentTodo: LiveData<Todo> get() = _currentTodo
    val _currentTodo: MutableLiveData<Todo> by lazy {
        val mutable = MutableLiveData<Todo>()
        mutable.value = Todo(-1, "", "", LocalDate.now().toEpochDay(), LocalDate.now().toEpochDay())
        mutable
    }

    /***Functions***/

    fun deleteTodo(todoId: Long) {
        viewModelScope.launch {
            todoRepo.deleteTodoById(todoId)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepo.updateTodo(todo)
        }
    }

    fun createTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepo.createTodo(todo)
        }
    }

    fun newTodo() {
        _currentTodo.value = Todo(-1, "", "", LocalDate.now().toEpochDay(), LocalDate.now().toEpochDay())
    }

    fun setCurrentTodo(todo: Todo) {
        _currentTodo.value = todo
    }

    fun deleteAllTodos() {
        viewModelScope.launch {
            todoRepo.deleteAll()
        }
    }
}