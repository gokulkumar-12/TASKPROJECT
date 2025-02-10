package com.example.taskapp.utils

import com.example.taskapp.TaskApplication

class AndroidUtils {


    companion object{
        fun runOnUIThread(runnable: Runnable, delay: Long) {
            runUIThread(runnable,delay)
        }

        private fun runUIThread(runnable: Runnable, delay: Long) {
            if (delay  > 0) {
                TaskApplication.applicationHandler.postDelayed(runnable, delay);
            } else {
                TaskApplication.applicationHandler.post(runnable);
            }
        }
    }
}