package com.egecius.egisbabylontechtest.postdetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.postslist.Post
import kotlinx.android.synthetic.main.activity_post_detail.*

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val post = intent.getSerializableExtra(KEY_POS) as Post

        postTitle.text = post.title
    }

    companion object {

        private const val KEY_POS = "KEY_POS"

        fun start(originActivity: Activity, post : Post) {
            val intent = Intent(originActivity, PostDetailActivity::class.java)
            intent.putExtra(KEY_POS, post)
            originActivity.startActivity(intent)
        }
    }
}