package com.egecius.egisbabylontechtest.features.showpostdetail.comments

import io.reactivex.Single

interface CommentsRepository {

    fun getComments(): Single<List<Comment>>
}