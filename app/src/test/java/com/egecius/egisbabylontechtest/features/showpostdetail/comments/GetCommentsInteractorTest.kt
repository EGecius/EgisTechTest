package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCommentsInteractorTest {

    private lateinit var sut: GetCommentsInteractor

    @Mock
    private lateinit var commentsRepository: CommentsRepository
    private val postId1 = 1

    private val postId2 = 2
    private val comment1 = Comment(1, postId1, "name 1", "body 1")
    private val comment2 = Comment(2, postId2, "name 2", "body 2")
    private val comment3 = Comment(3, postId1, "name 3", "body 3")
    private val comment4 = Comment(4, postId2, "name 4", "body 4")
    private val comment5 = Comment(5, postId1, "name 5", "body 5")

    private val listAllComments = listOf(comment1, comment2, comment3, comment4, comment5)
    private val commentsPost1 = listOf(comment1, comment3, comment5)

    @Before
    fun setUp() {
        sut = GetCommentsInteractor(commentsRepository)
    }

    @Test
    fun `gets comments for post 1`() {
        givenRepositoryWillReturnSuccessfully()

        val testObserver = sut.getComments(postId1).test()

        testObserver.assertResult(commentsPost1)
    }

    private fun givenRepositoryWillReturnSuccessfully() {
        given(commentsRepository.getComments()).willReturn(Single.just(listAllComments))
    }

}