package com.egecius.egisbabylontechtest.postdetail

class UserMapper {
    fun toUser(userJson: UserJson): User {
        return User(userJson.id, userJson.name)
    }
}