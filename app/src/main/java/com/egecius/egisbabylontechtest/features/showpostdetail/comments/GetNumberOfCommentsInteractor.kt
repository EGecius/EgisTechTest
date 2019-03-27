package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import io.reactivex.Single

open class GetNumberOfCommentsInteractor(private val commentsRepository: CommentsRepository) {

    open fun getNumberOfComments(postId: Int): Single<Int> {
        return commentsRepository.getComments()
            .toObservable()
            .flatMapIterable { it }
            .filter { it.postId == postId }
            .toList()
            .map { it.size }
    }

}
