package com.egecius.egisbabylontechtest.features.showpostdetail.user

import io.reactivex.Single

open class GetUserInteractor(private val userRepository: UserRepository) {

    open fun getUser(id: Int): Single<User> {
        return userRepository.getUser(id)
    }
}
