package com.egecius.egisbabylontechtest.postslist

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class ListActivityPresenter(private val getPostsInteractor: GetPostsInteractor) {

    private val compositeDisposable = CompositeDisposable()

    fun startPresenting(view: View) {
        val disposable = getPostsInteractor.getPosts()
            .subscribe(Consumer {
                view.showPosts(it)
            }, Consumer {
                view.showError()
            })

        compositeDisposable.add(disposable)
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    interface View {
        fun showPosts(posts: List<Post>)
        fun showError()
    }
}