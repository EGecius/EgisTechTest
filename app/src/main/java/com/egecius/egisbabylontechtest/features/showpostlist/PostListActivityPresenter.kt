package com.egecius.egisbabylontechtest.features.showpostlist

import com.egecius.egisbabylontechtest.features.showpostlist.post.GetPostsInteractor
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.infrastructure.InteractorSchedulers
import io.reactivex.disposables.CompositeDisposable

class PostListActivityPresenter(
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
                handleSuccessResult(it)
            }, {
                view.showError()
            })

        compositeDisposable.add(disposable)
    }

    private fun handleSuccessResult(it: List<Post>) {
        if (it.isEmpty()) {
            view.showError()
        } else {
            view.showPosts(it)
        }
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