package com.egecius.egisbabylontechtest.features.showpostlist

import com.egecius.egisbabylontechtest.features.showpostlist.post.NetworkPostsRepository
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.features.showpostlist.post.PostJson
import com.egecius.egisbabylontechtest.features.showpostlist.post.PostMapper
import com.egecius.egisbabylontechtest.features.showpostlist.post.persistency.PostsDao
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
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
    @Mock
    private lateinit var postsDao: PostsDao

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

    private val post = Post(
        postId,
        title,
        body,
        userId
    )
    private val listPosts = listOf(post)

    private val post2 = Post(
        4,
        "title 2",
        "body 2",
        12
    )
    private val listPostsCached = listOf(post2)

    @Before
    fun setUp() {
        sut = NetworkPostsRepository(networkService, PostMapper(), postsDao)
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

    @Test
    fun `caches fetched data to persistence`() {
        givenNetworkServiceWillSucceed()

        sut.getPosts().subscribe()

        verify(postsDao).insertPost(post)
    }

    @Test
    fun `uses cached persistence data, when network fetching fails`() {
        givenThereIsCachedData()
        givenNetworkServiceWillFail()

        val testObserver = sut.getPosts().test()

        testObserver.assertResult(listPostsCached)
    }

    private fun givenNetworkServiceWillFail() {
        given(networkService.getPosts()).willReturn(Single.error(Exception()))
    }

    private fun givenThereIsCachedData() {
        given(postsDao.loadAllPosts()).willReturn(listPostsCached)
    }

    @Test
    fun `does not cache to persistence, when network fetching fails`() {
        givenThereIsCachedData()
        givenNetworkServiceWillFail()

        sut.getPosts().subscribe()

        verify(postsDao, never()).insertPost(any())
    }

}