package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.postslist.Post

class PostDetailActivityPresenter {

    fun startPresenting(
        view: View,
        post: Post
    ) {
        view.showPost(post)
    }

    fun stopPresenting() {
        TODO("not implemented")
    }

    interface View{

        fun showPost(post: Post)
    }
}