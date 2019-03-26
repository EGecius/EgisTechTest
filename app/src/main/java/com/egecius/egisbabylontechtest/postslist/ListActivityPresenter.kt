package com.egecius.egisbabylontechtest.postslist

import com.egecius.egisbabylontechtest.InteractorSchedulers
import io.reactivex.disposables.CompositeDisposable

class ListActivityPresenter(
    private val getPostsInteractor: GetPostsInteractor,
    private val schedulers: InteractorSchedulers
) {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var view: View

    fun startPresenting(view: View) {
        this.view = view
        showPosts()
    }

    private fun showPosts() {
        val disposable = getPostsInteractor.getPosts()
            .subscribeOn(schedulers.getExecutionsScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showPosts(it)
            }, {
                view.showError()
            })

        compositeDisposable.add(disposable)
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    fun retryFetching() {
        showPosts()
    }

    interface View {
        fun showPosts(posts: List<Post>)
        fun showError()
    }
}