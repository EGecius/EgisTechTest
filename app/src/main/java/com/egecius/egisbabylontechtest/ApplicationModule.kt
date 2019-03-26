package com.egecius.egisbabylontechtest

import com.egecius.egisbabylontechtest.postslist.NetworkService
import com.egecius.egisbabylontechtest.postslist.PostsRetrofitAdapter
import com.egecius.egisbabylontechtest.postslist.di.AndroidInteractorSchedulers
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
        return PostsRetrofitAdapter().setupRetrofit()
    }

}
