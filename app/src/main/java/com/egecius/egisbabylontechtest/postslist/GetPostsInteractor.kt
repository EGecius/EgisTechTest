package com.egecius.egisbabylontechtest.postslist

import io.reactivex.Single

open class GetPostsInteractor(private val postsRepository : PostsRepository) {
    open fun getPosts() : Single<List<Post>> {
        return postsRepository.getPosts()
    }
}