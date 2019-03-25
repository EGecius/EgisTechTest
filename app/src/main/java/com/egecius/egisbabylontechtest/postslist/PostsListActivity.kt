package com.egecius.egisbabylontechtest.postslist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.egisbabylontechtest.MyApplication
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.di.ListActivityModule
import com.egecius.egisbabylontechtest.postdetail.PostDetailActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class PostsListActivity : AppCompatActivity(), ListActivityPresenter.View {

    private val listActivityAdapter = ListActivityAdapter(object : ListActivityAdapter.OnClickListener {
        override fun onClick(post: Post) {
            PostDetailActivity.start(this@PostsListActivity, post)
        }
    })

    @Inject
    lateinit var presenter: ListActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        injectDependencies()

        setupRecycler()
    }

    private fun injectDependencies() {
        val myApplication = application as MyApplication
        myApplication.applicationComponent.plus(ListActivityModule())
            .injectInto(this)
    }

    private fun setupRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listActivityAdapter
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
        listActivityAdapter.setData(posts)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showError() {
        Snackbar.make(parent_layout, getString(R.string.loading_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                presenter.retryFetching(this)
                progressBar.visibility = View.VISIBLE
            }
            .show()

        hideProgressBar()
    }

}
