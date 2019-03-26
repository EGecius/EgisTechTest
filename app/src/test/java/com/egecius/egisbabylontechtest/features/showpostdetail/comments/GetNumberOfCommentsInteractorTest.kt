package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNumberOfCommentsInteractorTest {

    private lateinit var sut: GetNumberOfCommentsInteractor

    @Mock
    private lateinit var commentsRepository: CommentsRepository

    private val postId1 = 1
    private val postId2 = 2

    private val comment1 = Comment(1, postId1, "name 1", "body 1")

    private val comment2 = Comment(2, postId2, "name 2", "body 2")
    private val comment3 = Comment(3, postId1, "name 3", "body 3")
    private val comment4 = Comment(4, postId2, "name 4", "body 4")
    private val comment5 = Comment(5, postId1, "name 5", "body 5")

    private val listComments = listOf(comment1, comment2, comment3, comment4, comment5)

    @Before
    fun setUp() {
        sut = GetNumberOfCommentsInteractor(commentsRepository)
    }

    @Test
    fun `gets number of comments for post 1`() {
        givenRepositoryWillReturnSuccessfully()

        val testObserver = sut.getNumberOfComments(postId1).test()

        testObserver.assertResult(3)
    }

    private fun givenRepositoryWillReturnSuccessfully() {
        given(commentsRepository.getComments()).willReturn(Single.just(listComments))
    }

    @Test
    fun `gets number of comments for post 2`() {
        givenRepositoryWillReturnSuccessfully()

        val testObserver = sut.getNumberOfComments(postId2).test()

        testObserver.assertResult(2)
    }

}