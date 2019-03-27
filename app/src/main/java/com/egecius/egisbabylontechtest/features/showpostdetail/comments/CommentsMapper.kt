package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import com.annimon.stream.Stream

class CommentsMapper {

    fun toComments(commentsJson: List<CommentJson>): List<Comment> {

        return Stream.of(commentsJson)
            .map { Comment(it.id, it.postId, it.name, it.body) }
            .toList()
    }
}