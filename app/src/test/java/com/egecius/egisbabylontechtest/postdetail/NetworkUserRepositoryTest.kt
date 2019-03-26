package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.postdetail.user.NetworkUserRepository
import com.egecius.egisbabylontechtest.postdetail.user.User
import com.egecius.egisbabylontechtest.postdetail.user.UserJson
import com.egecius.egisbabylontechtest.postdetail.user.UserMapper
import com.egecius.egisbabylontechtest.postslist.NetworkService
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkUserRepositoryTest {

    private lateinit var mSut: NetworkUserRepository

    @Mock
    private lateinit var networkService: NetworkService

    private val userId = 1
    private val name = "name"
    private val userName = "user name"

    private val userJson = UserJson(
        userId,
        name,
        userName,
        "email",
        null,
        "phone",
        "website",
        null
    )
    private val user = User(userId, name)

    private val userException = Exception()

    @Before
    fun setUp() {
        mSut = NetworkUserRepository(
            networkService,
            UserMapper()
        )
    }

    @Test
    fun `fetches user`() {
        givenNetworkServiceWillSucceed()

        val testObserver = mSut.getUser(userId).test()

        testObserver.assertResult(user)
    }

    private fun givenNetworkServiceWillSucceed() {
        given(networkService.getUser(userId)).willReturn(Single.just(userJson))
    }

    @Test
    fun `returns error`() {
        givenNetworkServiceWillFail()

        val testObserver = mSut.getUser(userId).test()

        testObserver.assertError(userException)
    }

    private fun givenNetworkServiceWillFail() {
        given(networkService.getUser(userId)).willReturn(Single.error(userException))
    }

}