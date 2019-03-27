package com.egecius.egisbabylontechtest.features.showpostdetail

import com.egecius.egisbabylontechtest.features.showpostdetail.comments.Comment
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.GetCommentsInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.GetNumberOfCommentsInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.User
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.infrastructure.TestInteractorSchedulers
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

    private lateinit var sut: PostDetailActivityPresenter

    @Mock
    private lateinit var view: PostDetailActivityPresenter.View
    @Mock
    private lateinit var getUserInteractor: GetUserInteractor
    @Mock
    private lateinit var getNumberOfCommentsInteractor: GetNumberOfCommentsInteractor
    @Mock
    private lateinit var getCommentsInteractor: GetCommentsInteractor

    private val userId = 18

    private val postId1 = 1
    private val postId2 = 1

    private val comment1 = Comment(1, postId1, "name 1", "body 1")
    private val comment3 = Comment(3, postId1, "name 3", "body 3")
    private val comment5 = Comment(5, postId1, "name 5", "body 5")

    private val commentsPost1 = listOf(comment1, comment3, comment5)

    @Before
    fun setUp() {
        sut = PostDetailActivityPresenter(
            getUserInteractor, getNumberOfCommentsInteractor, getCommentsInteractor,
            TestInteractorSchedulers()
        )
    }

    private val post = Post(postId2, "title", "body", userId)
    private val userName = "user name"

    private val user = User(13, userName, "userImage")

    @Test
    fun `shows post on start`() {
        givenGettingUserWillSucceed()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillSucceed()

        sut.startPresenting(view, post)

        verify(view).showPost(post)
    }

    @Test
    fun `show user name`() {
        givenGettingUserWillSucceed()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillSucceed()

        sut.startPresenting(view, post)

        verify(view).showUser(user)
    }

    private fun givenGettingUserWillSucceed() {
        given(getUserInteractor.getUser(userId)).willReturn(Single.just(user))
    }

    @Test
    fun `handles error`() {
        givenGettingUserWillFail()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillSucceed()

        sut.startPresenting(view, post)

        verify(view).showUserLoadingError()
    }

    private fun givenGettingUserWillFail() {
        given(getUserInteractor.getUser(userId)).willReturn(Single.error(Exception()))
    }

    @Test
    fun `retries loading user`() {
        givenGettingUserWillSucceed()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillSucceed()
        sut.startPresenting(view, post)

        sut.retryShowingUser()

        // called twice - 1st one from startPresenting()
        verify(view, times(2)).showUser(user)
    }

    @Test
    fun `shows number of comments`() {
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingUserWillSucceed()
        givenGettingCommentsWillSucceed()

        sut.startPresenting(view, post)

        verify(view).showNumberOfComments(3)
    }

    private fun givenGettingNumberOfCommentsWillSucceed() {
        given(getNumberOfCommentsInteractor.getNumberOfComments(1)).willReturn(Single.just(3))
    }

    @Test
    fun `handles error of getting number of comments`() {
        givenGettingNumberOfCommentsWillFail()
        givenGettingUserWillSucceed()
        givenGettingCommentsWillSucceed()

        sut.startPresenting(view, post)

        verify(view).showCommentLoadingError()
    }

    private fun givenGettingNumberOfCommentsWillFail() {
        given(getNumberOfCommentsInteractor.getNumberOfComments(1)).willReturn(Single.error(Exception()))
    }

    @Test
    fun `retries loading comments`() {
        givenGettingUserWillSucceed()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillSucceed()
        sut.startPresenting(view, post)

        sut.retryShowingComments()

        // called twice - 1st one from startPresenting()
        verify(view, times(2)).showNumberOfComments(3)
    }

    @Test
    fun `shows all comments`() {
        givenGettingUserWillSucceed()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillSucceed()

        sut.startPresenting(view, post)

        verify(view).showComments(commentsPost1)
    }

    private fun givenGettingCommentsWillSucceed() {
        given(getCommentsInteractor.getComments(postId2)).willReturn(Single.just(commentsPost1))
    }

    @Test
    fun `handles errors when fetching comments fails`() {
        givenGettingUserWillSucceed()
        givenGettingNumberOfCommentsWillSucceed()
        givenGettingCommentsWillFail()

        sut.startPresenting(view, post)

        verify(view).showCommentLoadingError()
    }

    private fun givenGettingCommentsWillFail() {
        given(getCommentsInteractor.getComments(postId2)).willReturn(Single.error(Exception()))
    }

}