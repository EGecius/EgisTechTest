package com.egecius.egisbabylontechtest.features.showpostdetail.user

import com.egecius.egisbabylontechtest.features.showpostslist.NetworkService
import io.reactivex.Single

class NetworkUserRepository(
    private val networkService: NetworkService,
    private val userMapper: UserMapper
) : UserRepository {

    override fun getUser(id: Int): Single<User> {
        return networkService.getUser(id)
            .map { userMapper.toUser(it) }
    }
}