package com.egecius.egisbabylontechtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setupRecycler()
    }

    private fun setupRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val listAdapter = ListActivityAdapter()
        recyclerView.adapter = listAdapter

        val setupRetrofit: PostsService = PostsRetrofitAdapter().setupRetrofit()

        // TODO: 25/03/2019 - temporary - fetch posts data
        setupRetrofit.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                listAdapter.setData(it)
                Log.d("Eg:ListActivity:35", "setupRecycler it: $it")
            }, Consumer {
                Log.w("Eg:ListActivity:35", "setupRecycler it: $it")
            })
    }

}
