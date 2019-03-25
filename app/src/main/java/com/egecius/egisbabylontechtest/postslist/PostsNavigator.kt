package com.egecius.egisbabylontechtest.postslist

import android.app.Activity
import com.egecius.egisbabylontechtest.postdetail.PostDetailActivity

class PostsNavigator(private val originActivity: Activity) {

    fun showPostDetail(postClick: PostClick) {
        PostDetailActivity.start(originActivity, postClick)
    }

}
