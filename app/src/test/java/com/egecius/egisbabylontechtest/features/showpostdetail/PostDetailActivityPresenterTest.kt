package com.egecius.egisbabylontechtest.features.showpostdetail

import com.egecius.egisbabylontechtest.TestInteractorSchedulers
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.GetNumberOfCommentsInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.User
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
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
class PostDetailActivityPresenterTest {

    private lateinit var mSut: PostDetailActivityPresenter

    @Mock
    private lateinit var view: PostDetailActivityPresenter.View
    @Mock
    private lateinit var getUserInteractor: GetUserInteractor

    @Mock
    private lateinit var getNumberOfCommentsInteractor: GetNumberOfCommentsInteractor
    private val userId = 18

    private val postId = 1

    @Before
    fun setUp() {
        mSut = PostDetailActivityPresenter(getUserInteractor, getNumberOfCommentsInteractor, TestInteractorSchedulers())
    }

    private val post = Post(postId, "title", "body", userId)
    private val userName = "user name"

    private val user = User(13, userName, "userImage")

    @Test
    fun `shows post on start`() {
        givenGettingUserWillSucceed()
        givenGettingCommentsWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showPost(post)
    }

    @Test
    fun `show user name`() {
        givenGettingUserWillSucceed()
        givenGettingCommentsWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showUser(user)
    }

    private fun givenGettingUserWillSucceed() {
        given(getUserInteractor.getUser(userId)).willReturn(Single.just(user))
    }

    @Test
    fun `handles error`() {
        givenGettingUserWillFail()
        givenGettingCommentsWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showUserLoadingError()
    }

    private fun givenGettingUserWillFail() {
        given(getUserInteractor.getUser(userId)).willReturn(Single.error(Exception()))
    }

    @Test
    fun `retries loading user`() {
        givenGettingUserWillSucceed()
        givenGettingCommentsWillSucceed()
        mSut.startPresenting(view, post)

        mSut.retryShowingUser()

        // called twice - 1st one from startPresenting()
        verify(view, times(2)).showUser(user)
    }

    @Test
    fun `shows number of comments`() {
        givenGettingCommentsWillSucceed()
        givenGettingUserWillSucceed()

        mSut.startPresenting(view, post)

        verify(view).showNumberOfComments(3)
    }

    private fun givenGettingCommentsWillSucceed() {
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
        givenGettingCommentsWillSucceed()
        mSut.startPresenting(view, post)

        mSut.retryShowingComments()

        // called twice - 1st one from startPresenting()
        verify(view, times(2)).showNumberOfComments(3)
    }

}