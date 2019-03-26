package com.egecius.egisbabylontechtest.postdetail.di

import com.egecius.egisbabylontechtest.InteractorSchedulers
import com.egecius.egisbabylontechtest.postdetail.*
import com.egecius.egisbabylontechtest.postslist.NetworkService
import dagger.Module
import dagger.Provides

@Module
class PostDetailModule {

    @Provides
    fun providesPostDetailPresenter(
        getUserInteractor: GetUserInteractor,
        interactorSchedulers: InteractorSchedulers
    ): PostDetailActivityPresenter {
        return PostDetailActivityPresenter(getUserInteractor, interactorSchedulers)
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

}
