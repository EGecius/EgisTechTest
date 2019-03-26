package com.egecius.egisbabylontechtest.features.showpostdetail.comments

data class CommentJson(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
)