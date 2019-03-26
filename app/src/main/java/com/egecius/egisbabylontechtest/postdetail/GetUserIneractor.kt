package com.egecius.egisbabylontechtest.postdetail

import io.reactivex.Single

class GetUserIneractor(private val userRepository: UserRepository) {

    fun getUser(id: Int): Single<User> {
        return userRepository.getUser(id)
    }
}
