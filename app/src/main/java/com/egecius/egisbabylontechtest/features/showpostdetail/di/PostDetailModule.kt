package com.egecius.egisbabylontechtest.features.showpostdetail.di

import com.egecius.egisbabylontechtest.features.showpostdetail.PostDetailActivityPresenter
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.CommentsMapper
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.CommentsRepository
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.GetNumberOfCommentsInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.NetworkCommentsRepository
import com.egecius.egisbabylontechtest.features.showpostdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.NetworkUserRepository
import com.egecius.egisbabylontechtest.features.showpostdetail.user.UserMapper
import com.egecius.egisbabylontechtest.features.showpostdetail.user.UserRepository
import com.egecius.egisbabylontechtest.infrastructure.InteractorSchedulers
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import dagger.Module
import dagger.Provides

@Module
class PostDetailModule {

    @Provides
    fun providesPostDetailActivityPresenter(
        getUserInteractor: GetUserInteractor,
        getNumberOfCommentsInteractor: GetNumberOfCommentsInteractor,
        interactorSchedulers: InteractorSchedulers
    ): PostDetailActivityPresenter {
        return PostDetailActivityPresenter(getUserInteractor, getNumberOfCommentsInteractor, interactorSchedulers)
    }

    @Provides
    fun provideGetUserInteractor(userRepository: UserRepository): GetUserInteractor {
        return GetUserInteractor(userRepository)
    }

    @Provides
    fun provideUserRepository(
        networkService: NetworkService,
        userMapper: UserMapper
    ): UserRepository {
        return NetworkUserRepository(networkService, userMapper)
    }

    @Provides
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }

    @Provides
    fun provideGetNumberOfCommentsInteractor(commentsRepository: CommentsRepository): GetNumberOfCommentsInteractor {
        return GetNumberOfCommentsInteractor(commentsRepository)
    }

    @Provides
    fun provideCommentsRepository(
        networkService: NetworkService,
        commentsMapper: CommentsMapper
    ): CommentsRepository {
        return NetworkCommentsRepository(networkService, commentsMapper)
    }

    @Provides
    fun provideCommentMapper(): CommentsMapper {
        return CommentsMapper()
    }

}
