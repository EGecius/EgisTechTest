package com.egecius.egisbabylontechtest

import com.egecius.egisbabylontechtest.postslist.di.AndroidInteractorSchedulers
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideInteractorSchedulers() : InteractorSchedulers {
        return AndroidInteractorSchedulers()
    }
}
