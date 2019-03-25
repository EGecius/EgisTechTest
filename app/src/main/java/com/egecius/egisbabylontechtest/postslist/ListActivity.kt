package com.egecius.egisbabylontechtest.postslist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.di.DaggerListActivityComponent
import com.egecius.egisbabylontechtest.di.ListActivityModule
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject

class ListActivity : AppCompatActivity(), ListActivityPresenter.View {

    private val listActivityAdapter = ListActivityAdapter()

    @Inject
    lateinit var presenter: ListActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        injectDependencies()

        setupRecycler()
    }

    private fun injectDependencies() {
        DaggerListActivityComponent.builder()
            .listActivityModule(ListActivityModule())
            .build()
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
        listActivityAdapter.setData(posts)
    }

    override fun showError() {
        TODO("not implemented")
    }

}
