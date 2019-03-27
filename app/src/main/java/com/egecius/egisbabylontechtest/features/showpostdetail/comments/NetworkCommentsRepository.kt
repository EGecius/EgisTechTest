package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import io.reactivex.Single

class NetworkCommentsRepository(
    private val networkService: NetworkService,
    private val commentsMapper: CommentsMapper
) : CommentsRepository {

    override fun getComments(): Single<List<Comment>> {
        return networkService.getComments()
            .map { commentsMapper.toComments(it) }
    }
}