package com.egecius.egisbabylontechtest.features.showpostdetail.user

import io.reactivex.Single

interface UserRepository {

    fun getUser(id: Int): Single<User>

}
