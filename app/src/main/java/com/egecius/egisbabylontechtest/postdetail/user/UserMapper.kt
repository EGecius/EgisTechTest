package com.egecius.egisbabylontechtest.postdetail.user

class UserMapper {
    fun toUser(userJson: UserJson): User {
        return User(userJson.id, userJson.name)
    }
}