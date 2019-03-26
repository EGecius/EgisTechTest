package com.egecius.egisbabylontechtest.features.showpostlist.post

import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Single<List<Post>>

}
