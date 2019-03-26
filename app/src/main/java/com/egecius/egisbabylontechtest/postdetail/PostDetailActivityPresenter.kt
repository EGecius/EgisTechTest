package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.InteractorSchedulers
import com.egecius.egisbabylontechtest.postdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.postslist.Post
import io.reactivex.disposables.CompositeDisposable

class PostDetailActivityPresenter(
    private val getUserInteractor: GetUserInteractor,
    private val schedulers: InteractorSchedulers
) {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var view: View
    private lateinit var post: Post

    fun startPresenting(
        view: View,
        post: Post
    ) {
        this.view = view
        this.post = post
        view.showPost(post)

        showUser()
    }

    private fun showUser() {
        val disposable = getUserInteractor.getUser(post.userId)
            .subscribeOn(schedulers.getExecutionsScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showUserName(it.name)
            }, { view.showUserLoadingError() })

        compositeDisposable.add(disposable)
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    fun retryShowingUser() {
        showUser()
    }

    interface View {

        fun showPost(post: Post)
        fun showUserName(userName: String)
        fun showUserLoadingError()
    }
}