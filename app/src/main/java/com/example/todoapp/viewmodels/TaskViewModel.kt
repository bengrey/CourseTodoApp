package com.example.todoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.database.task.Task
import com.example.todoapp.database.task.TaskDao
import kotlinx.coroutines.flow.Flow
class TaskViewModel(private val taskDao: TaskDao): ViewModel() {

    fun allTasks(): Flow<List<Task>> = taskDao.getAll()
}

class TaskViewModelFactory(
    private val taskDao: TaskDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}