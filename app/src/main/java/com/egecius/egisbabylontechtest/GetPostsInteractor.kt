package com.egecius.egisbabylontechtest

import io.reactivex.Single

class GetPostsInteractor(private val postsRepository : PostsRepository) {
    fun getPosts() : Single<List<Post>> {
        return postsRepository.getPosts()
    }
}