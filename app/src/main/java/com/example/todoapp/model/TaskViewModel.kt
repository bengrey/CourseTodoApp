package com.example.todoapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.database.task.Task
import com.example.todoapp.database.task.TaskDao
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao): ViewModel() {

    fun allTasks(): Flow<List<Task>> = taskDao.getAll()
    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Task>> = taskDao.getItems().asLiveData()

    /**
     * Updates an existing Item in the database.
     */
    fun updateTask(
        itemId: Int,
        taskName: String,
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, taskName)
        updateItem(updatedItem)
    }

    fun addNewItem(taskName: String) {
        val newItem = getNewItemEntry(taskName)
        insertItem(newItem)
    }

    private fun getNewItemEntry(taskName: String): Task {
        return Task(
            taskName = taskName
        )
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        taskName: String,
    ): Task {
        return Task(
            id = itemId,
            taskName = taskName
        )
    }

    private fun updateItem(item: Task) {
        viewModelScope.launch {
            taskDao.update(item)
        }
    }

    private fun insertItem(item: Task) {
        viewModelScope.launch {
            taskDao.insert(item)
        }
    }

    fun deleteTask(item: Task) {
        viewModelScope.launch {
            taskDao.delete(item)
        }
    }

    fun retrieveItem(id: Int): LiveData<Task> {
        return taskDao.getItem(id).asLiveData()
    }

    fun isEntryValid(taskName: String): Boolean {
        if (taskName.isBlank()) {
            return false
        }
        return true
    }

}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}