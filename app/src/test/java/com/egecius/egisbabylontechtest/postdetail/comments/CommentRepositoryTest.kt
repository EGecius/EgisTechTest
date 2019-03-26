package com.egecius.egisbabylontechtest.postdetail.comments

import com.egecius.egisbabylontechtest.postslist.NetworkService
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CommentRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private val commentId = 1

    private val commentsJsonList = listOf(CommentJson(commentId, 15, "name", "email", "body"))
    private val commentsList = listOf(Comment(commentId, 15, "name", "body"))

    private lateinit var sut: CommentRepository

    @Before
    fun setup() {
        sut = CommentRepository(networkService, CommentMapper())
    }

    @Test
    fun `gets all comments`() {
        givenFetchingCommentsWillSucceed()

        val testObserver = sut.getComments().test()

        testObserver.assertResult(commentsList)
    }

    private fun givenFetchingCommentsWillSucceed() {
        given(networkService.getComments()).willReturn(Single.just(commentsJsonList))
    }

}