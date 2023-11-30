package com.example.todoapp.database.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Annotate the class with @Entity to mark it as a table in the database
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Annotate the column name with @ColumnInfo and specify the name as "task_name"
    @ColumnInfo(name = "task_name")
    // Define a property for the name of the task
    val taskName: String,

    @ColumnInfo(name = "date") var date:String? = "",
    @ColumnInfo(name = "time") var time:String? = "",
)