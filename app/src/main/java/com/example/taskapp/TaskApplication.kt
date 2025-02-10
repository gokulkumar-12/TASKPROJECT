package com.example.taskapp

import android.app.Application
import android.os.Handler

class TaskApplication : Application() {

    companion object {
        val applicationHandler = Handler()
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onTerminate() {
        super.onTerminate()

    }
}