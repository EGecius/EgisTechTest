package com.egecius.egisbabylontechtest.features.showpostdetail.user

class UserMapper {

    fun toUser(userJson: UserJson): User {

        val avatarUrl = createAvatarUrl(userJson.name)

        return User(userJson.id, userJson.name, avatarUrl)
    }

    private fun createAvatarUrl(name: String): String {
        return avatarBaseUrl + name.replace(" ", "_") + ".png"
    }

    companion object {
        const val avatarBaseUrl = "https://api.adorable.io/avatars/250/"
    }
}