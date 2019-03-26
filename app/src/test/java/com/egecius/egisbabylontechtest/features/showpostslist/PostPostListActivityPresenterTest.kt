package com.egecius.egisbabylontechtest.features.showpostslist

import com.egecius.egisbabylontechtest.TestInteractorSchedulers
import com.egecius.egisbabylontechtest.features.showpostslist.post.GetPostsInteractor
import com.egecius.egisbabylontechtest.features.showpostslist.post.Post
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostPostListActivityPresenterTest {

    private lateinit var sut: PostListActivityPresenter

    @Mock
    lateinit var view: PostListActivityPresenter.View

    @Mock
    private lateinit var getPostsInteractor: GetPostsInteractor

    private val posts: List<Post> = listOf(
        Post(1, "title 1", "body 1", 15)
    )

    @Before
    fun setUp() {
        sut = PostListActivityPresenter(getPostsInteractor, TestInteractorSchedulers())
    }

    @Test
    fun `shows posts`() {
        givenPostsWillLoadSuccessfully()

        sut.startPresenting(view)

        verify(view).showPosts(posts)
    }

    private fun givenPostsWillLoadSuccessfully() {
        given(getPostsInteractor.getPosts()).willReturn(Single.just(posts))
    }


    @Test
    fun `handles error`() {
        given(getPostsInteractor.getPosts()).willReturn(Single.error(Exception()))

        sut.startPresenting(view)

        verify(view).showError()
    }

    @Test
    fun `retries fetching`() {
        givenPostsWillLoadSuccessfully()
        sut.startPresenting(view)

        sut.retryFetching()

        // called twice - 1st one from startPresenting()
        verify(view, times(2)).showPosts(posts)
    }

}