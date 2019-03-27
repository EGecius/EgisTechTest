package com.egecius.egisbabylontechtest.features.showpostdetail.comments

class CommentsMapper {

    fun toComments(commentsJson: List<CommentJson>): List<Comment> {

        val resultList = mutableListOf<Comment>()

        for (commentJson in commentsJson) {
            resultList.add(Comment(commentJson.id, commentJson.postId, commentJson.name, commentJson.body))
        }

        return resultList
    }
}