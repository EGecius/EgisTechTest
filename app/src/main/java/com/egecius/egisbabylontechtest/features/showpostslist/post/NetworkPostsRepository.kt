package com.egecius.egisbabylontechtest.features.showpostslist.post

import com.egecius.egisbabylontechtest.features.showpostslist.NetworkService
import io.reactivex.Single

class NetworkPostsRepository(
    private val networkService: NetworkService,
    private val mapper: PostMapper
) : PostsRepository {

    override fun getPosts(): Single<List<Post>> {
        return networkService.getPosts()
            .map { mapper.toPosts(it) }
    }

}