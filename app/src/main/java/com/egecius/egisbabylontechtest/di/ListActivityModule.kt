package com.egecius.egisbabylontechtest.di

import android.app.Activity
import com.egecius.egisbabylontechtest.InteractorSchedulers
import com.egecius.egisbabylontechtest.postslist.*
import dagger.Module
import dagger.Provides

@Module
class ListActivityModule(private val activity: Activity) {

    @Provides
    fun provideListActivityPresenter(
        getPostsInteractor: GetPostsInteractor,
        interactorSchedulers: InteractorSchedulers
    ): ListActivityPresenter {
        return ListActivityPresenter(getPostsInteractor, interactorSchedulers)
    }

    @Provides
    fun providePostsNavigator(): PostsNavigator {
        return PostsNavigator(activity)
    }

    @Provides
    fun provideGetPostsInteractor(postsRepository: PostsRepository): GetPostsInteractor {
        return GetPostsInteractor(postsRepository)
    }

    @Provides
    fun providePostsRepository(
        networkService: NetworkService,
        mapper: PostMapper
    ): PostsRepository {
        return NetworkPostsRepository(networkService, mapper)
    }

    @Provides
    fun providesPostsService() : NetworkService {
        return PostsRetrofitAdapter().setupRetrofit()
    }

    @Provides
    fun providesPostMapper(): PostMapper {
        return PostMapper()
    }

}
