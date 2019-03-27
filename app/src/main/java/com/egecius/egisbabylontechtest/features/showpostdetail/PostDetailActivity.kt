package com.egecius.egisbabylontechtest.features.showpostdetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.Comment
import com.egecius.egisbabylontechtest.features.showpostdetail.di.PostDetailModule
import com.egecius.egisbabylontechtest.features.showpostdetail.user.User
import com.egecius.egisbabylontechtest.features.showpostlist.PostClick
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.infrastructure.MyApplication
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_post_detail.*
import javax.inject.Inject

class PostDetailActivity : AppCompatActivity(), PostDetailActivityPresenter.View {

    private val recyclerAdapter = CommentsRecyclerAdapter()

    @Inject
    lateinit var presenter: PostDetailActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        injectDependencies()

        setupRecycler()
    }

    private fun injectDependencies() {
        val myApplication = application as MyApplication
        myApplication.applicationComponent.plus(PostDetailModule())
            .injectInto(this)
    }

    private fun setupRecycler() {
        commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        commentsRecyclerView.adapter = recyclerAdapter
    }

    override fun onStart() {
        super.onStart()
        val post = intent.getParcelableExtra(KEY_POS) as Post
        presenter.startPresenting(this, post)
    }

    override fun showComments(comments: List<Comment>) {
        recyclerAdapter.setData(comments)
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
        Picasso.get().load(user.image).into(userImageView, object : Callback {
            override fun onError(p0: Exception?) {
            }

            override fun onSuccess() {
                val bitmap = userImageView.drawable.toBitmap()
                val palette = Palette.Builder(bitmap).generate()
                val backgroundColour = palette.lightMutedSwatch!!.rgb
                parentLayout.setBackgroundColor(backgroundColour)
            }
        })
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