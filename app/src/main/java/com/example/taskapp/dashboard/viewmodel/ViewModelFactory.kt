package com.example.taskapp.dashboard.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        return ViewModel(context) as T
    }
}