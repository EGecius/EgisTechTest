package com.egecius.egisbabylontechtest.infrastructure

import android.app.Application
import com.egecius.egisbabylontechtest.DaggerApplicationComponent

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