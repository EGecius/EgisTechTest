package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.postdetail.user.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UserMapperTest {

    private var sut = UserMapper()

    private val userId = 1

    private val userName = "John Smith"
    private val userImage = "https://api.adorable.io/avatars/250/John_Smith.png"

    private val userJson = UserJson(
        userId,
        userName,
        "johny",
        "Sincere@april.biz",
        AddressJson(
            "Kulas Light",
            "Apt. 556",
            "Gwenborough",
            "92998-3874",
            GeoJson("-37.3159", "81.1496")
        ),
        "1-770-736-8031 x56442",
        "hildegard.org",
        CompanyJson(
            "Romaguera-Crona",
            "Multi-layered client-server neural-net",
            "harness real-time e-markets"
        )
    )

    @Test
    fun `maps to user`() {
        val user = sut.toUser(userJson)

        assertThat(user).isEqualTo(User(userId, userName, userImage))
    }

}