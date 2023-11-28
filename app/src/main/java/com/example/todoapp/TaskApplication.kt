package com.example.todoapp

import android.app.Application
import com.example.todoapp.database.AppDatabase

class TaskApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}