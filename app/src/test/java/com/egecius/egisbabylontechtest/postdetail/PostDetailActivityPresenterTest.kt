package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.TestInteractorSchedulers
import com.egecius.egisbabylontechtest.postdetail.comments.GetNumberOfCommentsInteractor
import com.egecius.egisbabylontechtest.postdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.postdetail.user.User
import com.egecius.egisbabylontechtest.postslist.Post
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PostDetailActivityPresenterTest {

    @Mock
    private lateinit var view: PostDetailActivityPresenter.View
    @Mock
    private lateinit var getUserInteractor: GetUserInteractor
    @Mock
    private lateinit var getNumberOfCommentsInteractor: GetNumberOfCommentsInteractor

    private lateinit var mSut: PostDetailActivityPresenter
    private val userId = 18

    private val postId = 1

    @Before
    fun setUp() {
        mSut = PostDetailActivityPresenter(getUserInteractor, getNumberOfCommentsInteractor, TestInteractorSchedulers())
    }

    private val post = Post(postId, "title", "body", userId)
    private val userName = "user name"

    private val user = User(13, userName)

    @Test
    fun `shows post on start`() {
        mSut.startPresenting(view, post)

        verify(view).showPost(post)
    }

    @Test
    fun `show user name`() {
        givenGettingUserWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showUserName(userName)
    }

    private fun givenGettingUserWillSucceed() {
        given(getUserInteractor.getUser(userId)).willReturn(Single.just(user))
    }

    @Test
    fun `handles error`() {
        givenGettingUserWillFail()

        mSut.startPresenting(view, post)

        verify(view).showUserLoadingError()
    }

    private fun givenGettingUserWillFail() {
        given(getUserInteractor.getUser(userId)).willReturn(Single.error(Exception()))
    }

    @Test
    fun `retries loading user`() {
        givenGettingUserWillSucceed()
        mSut.startPresenting(view, post)
        clearInvocations(view)

        mSut.retryShowingUser()

        verify(view).showUserName(userName)
    }

    @Test
    fun `shows number of comments`() {
        givenNumberOfCommentsIs3()
        givenGettingUserWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showNumberOfComments(3)
    }

    private fun givenNumberOfCommentsIs3() {
        given(getNumberOfCommentsInteractor.getNumberOfComments(1)).willReturn(Single.just(3))
    }


    @Test
    fun `handles error of getting number of comments`() {
        givenGettingCommentsWillFail()
        givenGettingUserWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showCommentLoadingError()
    }

    private fun givenGettingCommentsWillFail() {
        given(getNumberOfCommentsInteractor.getNumberOfComments(1)).willReturn(Single.error(Exception()))
    }

    @Test
    fun `retries loading comments`() {
        givenGettingUserWillSucceed()
        givenNumberOfCommentsIs3()
        mSut.startPresenting(view, post)
        clearInvocations(view)

        mSut.retryShowingComments()

        verify(view).showNumberOfComments(3)
    }

}