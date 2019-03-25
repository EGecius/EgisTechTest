package com.egecius.egisbabylontechtest

import com.egecius.egisbabylontechtest.di.AndroidInteractorSchedulers
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideInteractorSchedulers() : InteractorSchedulers {
        return AndroidInteractorSchedulers()
    }
}
