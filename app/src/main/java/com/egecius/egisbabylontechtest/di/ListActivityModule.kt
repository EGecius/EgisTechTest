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
    fun providePostsRepository(
        postsService: PostsService,
        mapper: PostMapper
    ): PostsRepository {
        return NetworkRepository(postsService, mapper)
    }

    @Provides
    fun providesPostsService() : PostsService {
        return PostsRetrofitAdapter().setupRetrofit()
    }

    @Provides
    fun providesPostMapper(): PostMapper {
        return PostMapper()
    }

}
