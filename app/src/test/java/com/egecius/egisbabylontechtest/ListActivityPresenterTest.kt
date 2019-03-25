package com.egecius.egisbabylontechtest

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListActivityPresenterTest {

    @Mock
    lateinit var view: ListActivityPresenter.View

    @Mock
    private lateinit var getPostsInteractor: GetPostsInteractor

    lateinit var mSut: ListActivityPresenter

    private val posts: List<Post> = listOf(Post("title 1"))

    @Before
    fun setUp() {
        mSut = ListActivityPresenter(getPostsInteractor)
    }

    @Test
    fun showsPosts() {
        given(getPostsInteractor.getPosts()).willReturn(Single.just(posts))

        mSut.startPresenting(view)

        verify(view).showPosts(posts)
    }


    @Test
    fun handlesError() {
        given(getPostsInteractor.getPosts()).willReturn(Single.error(Exception()))

        mSut.startPresenting(view)

        verify(view).showError()
    }

}