package com.egecius.egisbabylontechtest.features.showpostdetail.comments

data class Comment(
    val id: Int,
    val postId: Int,
    val name: String,
    val body: String
)