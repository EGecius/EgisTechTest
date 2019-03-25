package com.egecius.egisbabylontechtest.di

import com.egecius.egisbabylontechtest.ListActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class ListActivityModule {

    @Provides
    fun provideListActivityPresenter(): ListActivityPresenter {
        return ListActivityPresenter()
    }

}
