package com.egecius.egisbabylontechtest.postdetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.egecius.egisbabylontechtest.MyApplication
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.postdetail.di.PostDetailModule
import com.egecius.egisbabylontechtest.postdetail.user.User
import com.egecius.egisbabylontechtest.postslist.Post
import com.egecius.egisbabylontechtest.postslist.PostClick
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_post_detail.*
import javax.inject.Inject

class PostDetailActivity : AppCompatActivity(), PostDetailActivityPresenter.View {

    @Inject
    lateinit var presenter: PostDetailActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        injectDependencies()
    }

    private fun injectDependencies() {
        val myApplication = application as MyApplication
        myApplication.applicationComponent.plus(PostDetailModule())
            .injetInto(this)
    }

    override fun onStart() {
        super.onStart()
        val post = intent.getSerializableExtra(KEY_POS) as Post
        presenter.startPresenting(this, post)
    }

    override fun showUserLoadingError() {
        Snackbar.make(parentLayout, R.string.loading_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                presenter.retryShowingUser()
            }
            .show()
    }

    override fun showNumberOfComments(no: Int) {
        numberOfCommentsView.text = getString(R.string.no_of_comments, no)
    }

    override fun showCommentLoadingError() {
        Snackbar.make(parentLayout, R.string.loading_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                presenter.retryShowingComments()
            }
            .show()
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun showPost(post: Post) {
        postTitleView.text = post.title
        postBodyView.text = post.body
    }

    override fun showUser(user: User) {
        userNameView.text = user.name
        Picasso.get().load(user.image).into(userImageView)
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