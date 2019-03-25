package com.egecius.egisbabylontechtest

import android.app.Application

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
            .build()
    }
}