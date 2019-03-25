package com.egecius.egisbabylontechtest.postslist

import com.egecius.egisbabylontechtest.InteractorSchedulers
import io.reactivex.disposables.CompositeDisposable

class ListActivityPresenter(
    private val getPostsInteractor: GetPostsInteractor,
    private val schedulers: InteractorSchedulers
) {

    private val compositeDisposable = CompositeDisposable()

    fun startPresenting(view: View) {
        showPosts(view)
    }

    private fun showPosts(view: View) {
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

    fun retryFetching(view: View) {
        showPosts(view)
    }

    interface View {
        fun showPosts(posts: List<Post>)
        fun showError()
    }
}