package com.egecius.egisbabylontechtest.features.showpostdetail

import com.egecius.egisbabylontechtest.features.showpostdetail.user.NetworkUserRepository
import com.egecius.egisbabylontechtest.features.showpostdetail.user.User
import com.egecius.egisbabylontechtest.features.showpostdetail.user.UserJson
import com.egecius.egisbabylontechtest.features.showpostdetail.user.UserMapper
import com.egecius.egisbabylontechtest.infrastructure.NetworkService
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkUserRepositoryTest {

    private lateinit var sut: NetworkUserRepository

    @Mock
    private lateinit var networkService: NetworkService

    private val userId = 1
    private val name = "Leanne Graham"
    private val userImage = "https://api.adorable.io/avatars/250/Leanne_Graham.png"
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
    private val user = User(userId, name, userImage)

    private val userException = Exception()

    @Before
    fun setUp() {
        sut = NetworkUserRepository(
            networkService,
            UserMapper()
        )
    }

    @Test
    fun `fetches user`() {
        givenNetworkServiceWillSucceed()

        val testObserver = sut.getUser(userId).test()

        testObserver.assertResult(user)
    }

    private fun givenNetworkServiceWillSucceed() {
        given(networkService.getUser(userId)).willReturn(Single.just(userJson))
    }

    @Test
    fun `returns error`() {
        givenNetworkServiceWillFail()

        val testObserver = sut.getUser(userId).test()

        testObserver.assertError(userException)
    }

    private fun givenNetworkServiceWillFail() {
        given(networkService.getUser(userId)).willReturn(Single.error(userException))
    }

}