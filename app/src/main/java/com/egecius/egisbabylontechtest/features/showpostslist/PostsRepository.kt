package com.egecius.egisbabylontechtest.features.showpostslist

import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Single<List<Post>>

}
