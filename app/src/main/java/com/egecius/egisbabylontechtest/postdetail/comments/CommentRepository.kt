package com.egecius.egisbabylontechtest.postdetail.comments

import com.egecius.egisbabylontechtest.postslist.NetworkService
import io.reactivex.Single

class CommentRepository(
    private val networkService: NetworkService,
    private val commentMapper: CommentMapper
) {

    fun getComments(): Single<List<Comment>> {
        return networkService.getComments()
            .map { commentMapper.toComments(it) }
    }
}