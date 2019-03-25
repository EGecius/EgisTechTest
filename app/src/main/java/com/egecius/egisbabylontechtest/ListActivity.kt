package com.egecius.egisbabylontechtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setupRecycler()
    }

    private fun setupRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listActivityAdapter = ListActivityAdapter()
        recyclerView.adapter = listActivityAdapter

        // TODO: 25/03/2019 dummy data to be removed later
        listActivityAdapter.setData(listOf(Post("post 1"), Post("post 2"), Post("post 3")))
    }

}
