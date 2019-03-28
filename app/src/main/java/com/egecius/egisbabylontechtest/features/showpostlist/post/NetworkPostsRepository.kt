package com.egecius.egisbabylontechtest.features.showpostlist.post

import com.egecius.egisbabylontechtest.features.showpostlist.post.persistency.PostsDao
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import io.reactivex.Single

class NetworkPostsRepository(
    private val networkService: NetworkService,
    private val mapper: PostMapper,
    private val postsDao: PostsDao
) : PostsRepository {

    override fun getPosts(): Single<List<Post>> {

        return networkService.getPosts()
            .map { mapper.toPosts(it) }
            .doOnEvent { list, _ ->
                updateDatabase(list)
            }
    }

    private fun updateDatabase(list: List<Post>) {
        for (post in list) {
            postsDao.insertPost(post)
        }
    }


}