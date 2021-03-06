package com.egecius.egisbabylontechtest.features.showpostlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.features.showpostlist.di.ListActivityModule
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.infrastructure.MyApplication
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class PostListActivity : AppCompatActivity(), PostListActivityPresenter.View {

    @Inject
    lateinit var presenter: PostListActivityPresenter
    @Inject
    lateinit var navigator: PostListActivityNavigator

    private val recyclerAdapter = PostListRecyclerAdapter(object : PostListRecyclerAdapter.OnClickListener {
        override fun onClick(postClick: PostClick) {
            navigator.showPostDetail(postClick)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        injectDependencies()

        setupRecycler()
    }

    private fun injectDependencies() {
        val myApplication = application as MyApplication
        myApplication.applicationComponent.plus(ListActivityModule(this))
            .injectInto(this)
    }

    private fun setupRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }

    override fun onStart() {
        super.onStart()
        presenter.startPresenting(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.stopPresenting()
    }

    override fun showPosts(posts: List<Post>) {
        hideProgressBar()
        recyclerAdapter.setData(posts)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showError() {
        Snackbar.make(parentLayout, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                presenter.retryFetching()
                progressBar.visibility = View.VISIBLE
            }
            .show()

        hideProgressBar()
    }

}
