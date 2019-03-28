package com.egecius.egisbabylontechtest.infrastructure

import android.content.Context
import com.egecius.egisbabylontechtest.features.showpostlist.di.AndroidInteractorSchedulers
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val applicationContext: Context) {

    @Provides
    fun providesContext(): Context {
        return applicationContext
    }

    @Provides
    fun provideInteractorSchedulers(): InteractorSchedulers {
        return AndroidInteractorSchedulers()
    }

    @Provides
    fun providesNetworkService(): NetworkService {
        return RetrofitAdapter().setupRetrofit()
    }

}
