package com.egecius.egisbabylontechtest.postslist

import io.reactivex.Single

class NetworkRepository(
    private val postsService: PostsService,
    private val mapper: PostMapper
) : PostsRepository {

    override fun getPosts(): Single<List<Post>> {
        return postsService.getPosts()
            .map { mapper.toPosts(it) }
    }

}