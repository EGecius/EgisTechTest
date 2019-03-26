package com.egecius.egisbabylontechtest.features.showpostlist

import com.egecius.egisbabylontechtest.features.showpostlist.post.NetworkPostsRepository
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.features.showpostlist.post.PostJson
import com.egecius.egisbabylontechtest.features.showpostlist.post.PostMapper
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkPostsRepositoryTest {

    private lateinit var sut: NetworkPostsRepository

    @Mock
    private lateinit var networkService: NetworkService

    private val userId = 1

    private val postId = 2

    private val title = "title"
    private val body = "body"

    private val listPostsJson = listOf(
        PostJson(
            userId,
            postId,
            title,
            body
        )
    )
    private val listPosts = listOf(
        Post(
            postId,
            title,
            body,
            userId
        )
    )

    @Before
    fun setUp() {
        sut = NetworkPostsRepository(
            networkService,
            PostMapper()
        )
    }

    @Test
    fun `gets posts`() {
        givenNetworkServiceWillSucceed()

        val testObserver = sut.getPosts().test()

        testObserver.assertResult(listPosts)
    }

    private fun givenNetworkServiceWillSucceed() {
        given(networkService.getPosts()).willReturn(Single.just(listPostsJson))
    }

}