package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.postslist.NetworkService
import io.reactivex.Single

class NetworkUsersRepository(
    private val networkService: NetworkService,
    private val userMapper: UserMapper
) : UserRepository {

    override fun getUser(id: Int): Single<User> {
        TODO("not implemented")
    }
}