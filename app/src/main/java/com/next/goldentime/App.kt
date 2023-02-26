package com.next.goldentime

import android.app.Application
import android.content.Context
import com.next.goldentime.usecase.message.MessageUseCase

class App : Application() {
    companion object {
        private lateinit var instance: App

        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        MessageUseCase.subscribeSOS()
    }
}