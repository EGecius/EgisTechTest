package com.egecius.egisbabylontechtest.features.showpostlist.post

import java.io.Serializable

data class Post(val id: Int, val title: String, val body: String, val userId: Int) : Serializable