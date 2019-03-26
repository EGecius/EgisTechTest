package com.egecius.egisbabylontechtest.infrastructure

import com.egecius.egisbabylontechtest.features.showpostslist.di.AndroidInteractorSchedulers
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideInteractorSchedulers() : InteractorSchedulers {
        return AndroidInteractorSchedulers()
    }

    @Provides
    fun providesPostsService() : NetworkService {
        return RetrofitAdapter().setupRetrofit()
    }

}
