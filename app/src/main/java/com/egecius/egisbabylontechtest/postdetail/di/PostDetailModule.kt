package com.egecius.egisbabylontechtest.postdetail.di

import com.egecius.egisbabylontechtest.postdetail.PostDetailActivityPresenter
import dagger.Module
import dagger.Provides

@Module
class PostDetailModule {

    @Provides
    fun providesPostDetailPresenter(): PostDetailActivityPresenter {
        return PostDetailActivityPresenter()
    }

}
