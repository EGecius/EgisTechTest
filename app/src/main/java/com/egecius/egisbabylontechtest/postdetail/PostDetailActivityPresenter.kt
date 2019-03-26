package com.egecius.egisbabylontechtest.postdetail

import com.egecius.egisbabylontechtest.InteractorSchedulers
import com.egecius.egisbabylontechtest.postslist.Post
import io.reactivex.disposables.CompositeDisposable

class PostDetailActivityPresenter(
    private val getUserInteractor: GetUserInteractor,
    private val schedulers: InteractorSchedulers
) {

    private val compositeDisposable = CompositeDisposable()

    fun startPresenting(
        view: View,
        post: Post
    ) {
        view.showPost(post)

        val disposable = getUserInteractor.getUser(post.userId)
            .subscribeOn(schedulers.getExecutionsScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showUserName(it.name)
            }, { view.showError() })

        compositeDisposable.add(disposable)
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    interface View {

        fun showPost(post: Post)
        fun showUserName(userName: String)
        fun showError()
    }
}