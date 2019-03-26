package com.egecius.egisbabylontechtest.features.showpostslist.di

import android.app.Activity
import com.egecius.egisbabylontechtest.infrastructure.InteractorSchedulers
import com.egecius.egisbabylontechtest.features.showpostslist.*
import com.egecius.egisbabylontechtest.features.showpostslist.post.GetPostsInteractor
import com.egecius.egisbabylontechtest.features.showpostslist.post.NetworkPostsRepository
import com.egecius.egisbabylontechtest.features.showpostslist.post.PostMapper
import com.egecius.egisbabylontechtest.features.showpostslist.post.PostsRepository
import com.egecius.egisbabylontechtest.infrastructure.ActivityNavigator
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
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
    fun providePostsNavigator(): ActivityNavigator {
        return ActivityNavigator(activity)
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
        return NetworkPostsRepository(
            networkService,
            mapper
        )
    }

    @Provides
    fun providesPostMapper(): PostMapper {
        return PostMapper()
    }

}
