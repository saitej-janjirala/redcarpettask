package com.example.redcarpettask.utils

import android.app.Application

class RedCarpetApplication:Application() {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: RedCarpetApplication
        fun getInstance() = instance
    }
}