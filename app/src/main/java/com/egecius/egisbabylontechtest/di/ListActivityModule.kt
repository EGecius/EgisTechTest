package com.egecius.egisbabylontechtest.di

import com.egecius.egisbabylontechtest.InteractorSchedulers
import com.egecius.egisbabylontechtest.postslist.*
import dagger.Module
import dagger.Provides

@Module
class ListActivityModule {

    @Provides
    fun provideListActivityPresenter(
        getPostsInteractor: GetPostsInteractor,
        interactorSchedulers: InteractorSchedulers
    ): ListActivityPresenter {
        return ListActivityPresenter(getPostsInteractor, interactorSchedulers)
    }

    @Provides
    fun provideGetPostsInteractor(postsRepository: PostsRepository): GetPostsInteractor {
        return GetPostsInteractor(postsRepository)
    }

    @Provides
    fun providePostsRepository(postsService: PostsService): PostsRepository {
        return NetworkRepository(postsService)
    }

    @Provides
    fun providesPostsService() : PostsService {
        return PostsRetrofitAdapter().setupRetrofit()
    }

    // TODO: 25/03/2019 move this to application module
    @Provides
    fun provideInteractorSchedulers() : InteractorSchedulers {
        return AndroidInteractorSchedulers()
    }

}
