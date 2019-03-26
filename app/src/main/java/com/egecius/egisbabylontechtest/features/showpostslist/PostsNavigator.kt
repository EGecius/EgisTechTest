package com.egecius.egisbabylontechtest.features.showpostslist

import android.app.Activity
import com.egecius.egisbabylontechtest.features.showpostdetail.PostDetailActivity

class PostsNavigator(private val originActivity: Activity) {

    fun showPostDetail(postClick: PostClick) {
        PostDetailActivity.start(originActivity, postClick)
    }

}
