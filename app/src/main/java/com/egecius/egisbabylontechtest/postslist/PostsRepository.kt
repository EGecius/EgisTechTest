package com.egecius.egisbabylontechtest.postslist

import io.reactivex.Single

interface PostsRepository {

    fun getPosts(): Single<List<Post>>

}
