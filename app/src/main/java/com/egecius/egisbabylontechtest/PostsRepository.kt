package com.egecius.egisbabylontechtest

import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Single<List<Post>>

}
