package com.egecius.egisbabylontechtest.postslist

import android.app.Activity
import com.egecius.egisbabylontechtest.postdetail.PostDetailActivity

class PostsNavigator(private val originActivity: Activity) {

    fun showPostDetail(post: Post) {
        PostDetailActivity.start(originActivity, post)
    }

}
