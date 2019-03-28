package com.egecius.egisbabylontechtest.features.showpostlist.di

import android.app.Activity
import android.content.Context
import com.egecius.egisbabylontechtest.features.showpostlist.PostListActivityNavigator
import com.egecius.egisbabylontechtest.features.showpostlist.PostListActivityPresenter
import com.egecius.egisbabylontechtest.features.showpostlist.post.GetPostsInteractor
import com.egecius.egisbabylontechtest.features.showpostlist.post.NetworkPostsRepository
import com.egecius.egisbabylontechtest.features.showpostlist.post.PostMapper
import com.egecius.egisbabylontechtest.features.showpostlist.post.PostsRepository
import com.egecius.egisbabylontechtest.features.showpostlist.post.persistency.PostsDao
import com.egecius.egisbabylontechtest.features.showpostlist.post.persistency.PostsDatabase
import com.egecius.egisbabylontechtest.infrastructure.InteractorSchedulers
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import dagger.Module
import dagger.Provides

@Module
class ListActivityModule(private val activity: Activity) {

    @Provides
    fun providePostListActivityPresenter(
        getPostsInteractor: GetPostsInteractor,
        interactorSchedulers: InteractorSchedulers
    ): PostListActivityPresenter {
        return PostListActivityPresenter(getPostsInteractor, interactorSchedulers)
    }

    @Provides
    fun provideActivityNavigator(): PostListActivityNavigator {
        return PostListActivityNavigator(activity)
    }

    @Provides
    fun provideGetPostsInteractor(postsRepository: PostsRepository): GetPostsInteractor {
        return GetPostsInteractor(postsRepository)
    }

    @Provides
    fun providePostsRepository(
        networkService: NetworkService,
        mapper: PostMapper,
        postsDao: PostsDao
    ): PostsRepository {
        return NetworkPostsRepository(networkService, mapper, postsDao)
    }

    @Provides
    fun providesPostMapper(): PostMapper {
        return PostMapper()
    }

    @Provides
    fun providePostsDao(context: Context): PostsDao {
        // TODO: 28/03/2019 use application context rather than Activity
        return PostsDatabase.getInstance(context).postsDao()
    }

}
