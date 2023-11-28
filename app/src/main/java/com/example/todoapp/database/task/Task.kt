package com.example.todoapp.database.task

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.todoapp.database.DateConverter
import java.sql.Date

// Annotate the class with @Entity to mark it as a table in the database
@Entity(tableName = "tasks")
data class Task(
    // Annotate the primary key with @PrimaryKey and set autoGenerate to true
    @PrimaryKey(autoGenerate = true)
    // Annotate the column name with @ColumnInfo and specify the name as "id"
    @ColumnInfo(name = "id")
    // Define a property for the id of the task
    val id: Int = 0,

    // Annotate the column name with @ColumnInfo and specify the name as "task_name"
    @ColumnInfo(name = "task_name")
    // Define a property for the name of the task
    val taskName: String,

    @ColumnInfo(name = "date") var date:String? = "",
    @ColumnInfo(name = "time") var time:String? = "",
)