package com.egecius.egisbabylontechtest

import io.reactivex.Single

class NetworkRepository (private val postsService: PostsService) : PostsRepository {

    override fun getPosts(): Single<List<Post>> {
        return postsService.getPosts()
    }

}