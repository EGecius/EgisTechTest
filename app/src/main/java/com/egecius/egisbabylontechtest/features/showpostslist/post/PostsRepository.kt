package com.egecius.egisbabylontechtest.features.showpostslist.post

import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Single<List<Post>>

}
