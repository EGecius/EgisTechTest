package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.postslist.Post
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostDetailActivityPresenterTest {

    @Mock
    private lateinit var view: PostDetailActivityPresenter.View

    private lateinit var mSut: PostDetailActivityPresenter

    private val userId = 18
    private val postId = 1

    @Before
    fun setUp() {
        mSut = PostDetailActivityPresenter()
    }

    private val post = Post(postId, "title", "body", userId)

    @Test
    fun `shows post on start`() {
        mSut.startPresenting(view, post)

        verify(view).showPost(post)
    }

}