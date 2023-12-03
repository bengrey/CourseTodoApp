package com.example.todoapp.adapter

import android.util.Log
import com.example.todoapp.database.task.Task

interface RecyclerViewInterface {
    fun onItemClick(task : Task)
}