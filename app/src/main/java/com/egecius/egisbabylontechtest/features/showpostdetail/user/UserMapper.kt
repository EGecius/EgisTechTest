package com.egecius.egisbabylontechtest.features.showpostdetail.user

class UserMapper {
    fun toUser(userJson: UserJson): User {

        val avatarUrl = generateAvatarUrl(userJson.name)

        return User(userJson.id, userJson.name, avatarUrl)
    }

    private fun generateAvatarUrl(name: String): String {
        return avatarBaseUrl + name.replace(" ", "_") + ".png"
    }

    companion object {
        const val avatarBaseUrl = "https://api.adorable.io/avatars/250/"
    }
}