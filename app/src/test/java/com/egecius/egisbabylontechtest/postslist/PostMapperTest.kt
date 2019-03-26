package com.egecius.egisbabylontechtest.postslist

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PostMapperTest {

    private var mSut = PostMapper()

    private val title0 = "title 0"
    private val body0 = "body 0"
    private val postId0 = 1
    private val userId0 = 1

    private val title1 = "title 1"
    private val body1 = "body 1"
    private val postId1 = 2
    private val userId1 = 2

    private val postJson0 = PostJson(userId0, postId0, title0, body0)
    private val postJson1 = PostJson(userId1, postId1, title1, body1)

    private val listPostsJson: List<PostJson> = listOf(postJson0, postJson1)

    @Test
    fun `maps to posts`() {
        val listPosts = mSut.toPosts(listPostsJson)

        assertThat(listPosts).containsExactly(Post(title0, body0), Post(title1, body1))
    }

}