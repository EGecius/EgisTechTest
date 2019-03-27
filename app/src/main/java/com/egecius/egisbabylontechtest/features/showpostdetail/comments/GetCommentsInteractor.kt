package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import io.reactivex.Single

open class GetCommentsInteractor(private val commentsRepository: CommentsRepository) {

    fun getComments(postId: Int): Single<List<Comment>> {
        return commentsRepository.getComments()
            .toObservable()
            .flatMapIterable { it }
            .filter { it.postId == postId }
            .toList()
    }

}