package com.example.todoapp.database.task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    // Annotate the function with @Insert to mark it as an insert operation
    @Insert
    // Define a function that inserts a task into the database
    fun insertTask(task: Task)

    // Annotate the function with @Update to mark it as an update operation
    @Update
    // Define a function that updates a task in the database
    fun updateTask(task: Task)

    // Annotate the function with @Query and specify the SQL query to perform
    @Query("DELETE FROM tasks WHERE id = :taskId")
    // Define a function that deletes a task from the database by id
    fun deleteTask(taskId: Int)

    // Annotate the function with @Query and specify the SQL query to perform
//    @Query("SELECT * FROM tasks WHERE id = :taskId")
//    // Define a function that returns a task from the database by id
//    fun getTask(stopName: String): Flow<List<Task>>

    // Annotate the function with @Query and specify the SQL query to perform
    @Query("SELECT * FROM tasks ORDER BY date DESC")
    // Define a function that returns a list of all tasks from the database in descending order of creation time
    fun getAll(): Flow<List<Task>>
}