package com.idan.app.module.common

import android.app.Application

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    companion object {
        lateinit var app: App
        fun getContext() = app.applicationContext
    }

}