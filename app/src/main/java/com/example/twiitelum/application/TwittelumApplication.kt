package com.example.twiitelum.application

import android.app.Application

class TwittelumApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }
    companion object {
        lateinit var application: TwittelumApplication
        fun getInstance(): TwittelumApplication{
            return application
        }
    }
}