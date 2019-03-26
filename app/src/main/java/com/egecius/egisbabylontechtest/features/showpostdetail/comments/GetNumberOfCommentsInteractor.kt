package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import io.reactivex.Single
import io.reactivex.functions.Function

open class GetNumberOfCommentsInteractor(private val commentsRepository: CommentsRepository) {

    open fun getNumberOfComments(postId: Int): Single<Int> {
        return commentsRepository.getComments()
            .map(object : Function<List<Comment>, List<Comment>> {
                override fun apply(allComments: List<Comment>): List<Comment> {

                    val result = mutableListOf<Comment>()
                    for (comment in allComments) {
                        if (comment.postId == postId) {
                            result.add(comment)
                        }
                    }

                    return result
                }
            })
            .map { it.size }
    }


}