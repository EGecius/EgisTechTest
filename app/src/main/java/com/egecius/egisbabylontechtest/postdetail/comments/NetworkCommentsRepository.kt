package com.egecius.egisbabylontechtest.postdetail.comments

import com.egecius.egisbabylontechtest.postslist.NetworkService
import io.reactivex.Single

interface CommentsRepository {
    fun getComments(): Single<List<Comment>>
}

class NetworkCommentsRepository(
    private val networkService: NetworkService,
    private val commentMapper: CommentMapper
) : CommentsRepository {

    override fun getComments(): Single<List<Comment>> {
        return networkService.getComments()
            .map { commentMapper.toComments(it) }
    }
}