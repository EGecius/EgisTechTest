package com.egecius.egisbabylontechtest.infrastructure

import android.app.Activity
import com.egecius.egisbabylontechtest.features.showpostdetail.PostDetailActivity
import com.egecius.egisbabylontechtest.features.showpostlist.PostClick

class ActivityNavigator(private val originActivity: Activity) {

    fun showPostDetail(postClick: PostClick) {
        PostDetailActivity.start(originActivity, postClick)
    }

}
