package com.egecius.egisbabylontechtest.postdetail.comments

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommentMapperTest {

    private var sut = CommentMapper()

    private val commentId1 = 1

    private val postId1 = 12

    private val commentJson = CommentJson(commentId1, postId1, "name", "email", "body")
    private val listCommentsJson = listOf(commentJson)
    private val listComments = listOf(Comment(commentId1, postId1, "name", "body"))

    @Test
    fun `maps to comment`() {
        val comment = sut.toComments(listCommentsJson)

        assertThat(comment).isEqualTo(listComments)
    }

}