package com.egecius.egisbabylontechtest.features.showpostslist.post

import java.io.Serializable

data class Post(val id: Int, val title: String, val body: String, val userId: Int) : Serializable