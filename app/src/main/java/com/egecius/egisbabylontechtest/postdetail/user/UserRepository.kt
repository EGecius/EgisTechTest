package com.egecius.egisbabylontechtest.postdetail.user

import io.reactivex.Single

interface UserRepository {

    fun getUser(id: Int): Single<User>

}
