package com.egecius.egisbabylontechtest.features.showpostlist.post

import io.reactivex.Single

open class GetPostsInteractor(private val postsRepository : PostsRepository) {
    open fun getPosts() : Single<List<Post>> {
        return postsRepository.getPosts()
    }
}