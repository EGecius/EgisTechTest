package com.egecius.egisbabylontechtest.features.showpostlist.post

import com.annimon.stream.Stream


class PostMapper {

    fun toPosts(postsJson: List<PostJson>): List<Post> {

        return Stream.of(postsJson)
            .map{Post(it.id, it.title, it.body, it.userId)}
            .toList()
    }
}