package com.egecius.egisbabylontechtest.postdetail

import io.reactivex.Single

interface UserRepository {

    fun getUser(id: Int): Single<User>

}
