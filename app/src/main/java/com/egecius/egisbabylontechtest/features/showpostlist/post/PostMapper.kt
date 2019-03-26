package com.egecius.egisbabylontechtest.features.showpostlist.post


class PostMapper {

    fun toPosts(postsJson: List<PostJson>): List<Post> {

        val resultList = mutableListOf<Post>()

        for (postJson in postsJson) {
            resultList.add(
                Post(
                    postJson.id,
                    postJson.title,
                    postJson.body,
                    postJson.userId
                )
            )
        }

        return resultList
    }
}