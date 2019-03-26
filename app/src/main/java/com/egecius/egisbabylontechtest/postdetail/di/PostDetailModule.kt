package com.egecius.egisbabylontechtest.postdetail.di

import com.egecius.egisbabylontechtest.InteractorSchedulers
import com.egecius.egisbabylontechtest.postdetail.PostDetailActivityPresenter
import com.egecius.egisbabylontechtest.postdetail.comments.CommentMapper
import com.egecius.egisbabylontechtest.postdetail.comments.CommentsRepository
import com.egecius.egisbabylontechtest.postdetail.comments.GetNumberOfCommentsInteractor
import com.egecius.egisbabylontechtest.postdetail.comments.NetworkCommentsRepository
import com.egecius.egisbabylontechtest.postdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.postdetail.user.NetworkUserRepository
import com.egecius.egisbabylontechtest.postdetail.user.UserMapper
import com.egecius.egisbabylontechtest.postdetail.user.UserRepository
import com.egecius.egisbabylontechtest.postslist.NetworkService
import dagger.Module
import dagger.Provides

@Module
class PostDetailModule {

    @Provides
    fun providesPostDetailPresenter(
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
        commentMapper: CommentMapper
    ): CommentsRepository {
        return NetworkCommentsRepository(networkService, commentMapper)
    }

    @Provides
    fun provideCommentMapper(): CommentMapper {
        return CommentMapper()
    }

}
