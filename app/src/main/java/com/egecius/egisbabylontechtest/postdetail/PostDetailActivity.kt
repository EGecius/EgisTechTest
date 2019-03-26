package com.egecius.egisbabylontechtest.postdetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.postslist.Post
import com.egecius.egisbabylontechtest.postslist.PostClick
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val post = intent.getSerializableExtra(KEY_POS) as Post

        postTitleView.text = post.title
        postBodyView.text = post.body
    }

    companion object {

        private const val KEY_POS = "KEY_POS"
        private const val postTitle = "post_title"

        fun start(
            originActivity: Activity,
            postClick: PostClick
        ) {
            val intent = Intent(originActivity, PostDetailActivity::class.java)
            intent.putExtra(KEY_POS, postClick.post)
            val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(originActivity, postClick.titleView, postTitle)
            originActivity.startActivity(intent, options.toBundle())
        }
    }
}