package com.egecius.egisbabylontechtest.features.showpostlist

import android.app.Activity
import com.egecius.egisbabylontechtest.features.showpostdetail.PostDetailActivity

class PostListActivityNavigator(private val originActivity: Activity) {

    fun showPostDetail(postClick: PostClick) {
        PostDetailActivity.start(originActivity, postClick)
    }

}
