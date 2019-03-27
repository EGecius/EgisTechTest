package com.egecius.egisbabylontechtest.features.showpostdetail

import com.egecius.egisbabylontechtest.features.showpostdetail.comments.Comment
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.GetCommentsInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.GetNumberOfCommentsInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.GetUserInteractor
import com.egecius.egisbabylontechtest.features.showpostdetail.user.User
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post
import com.egecius.egisbabylontechtest.infrastructure.InteractorSchedulers
import io.reactivex.disposables.CompositeDisposable

class PostDetailActivityPresenter(
    private val getUserInteractor: GetUserInteractor,
    private val getNumberOfCommentsInteractor: GetNumberOfCommentsInteractor,
    private val getCommentsInteractor: GetCommentsInteractor,
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
        showNumberOfComments()
        showComments()
    }

    private fun showUser() {
        val disposable = getUserInteractor.getUser(post.userId)
            .subscribeOn(schedulers.getExecutionsScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showUser(it)
            }, { view.showUserLoadingError() })

        compositeDisposable.add(disposable)
    }

    private fun showNumberOfComments() {
        val disposable = getNumberOfCommentsInteractor.getNumberOfComments(post.id)
            .subscribeOn(schedulers.getExecutionsScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showNumberOfComments(it)
            }, { view.showCommentLoadingError() })

        compositeDisposable.add(disposable)
    }

    private fun showComments() {
        val disposable = getCommentsInteractor.getComments(post.id)
            .subscribeOn(schedulers.getExecutionsScheduler())
            .observeOn(schedulers.getPostExecutionScheduler())
            .subscribe({
                view.showComments(it)
            }, { view.showCommentLoadingError() })

        compositeDisposable.add(disposable)
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    fun retryShowingUser() {
        showUser()
    }

    fun retryShowingComments() {
        showNumberOfComments()
    }

    interface View {

        fun showPost(post: Post)
        fun showUser(user: User)
        fun showUserLoadingError()
        fun showNumberOfComments(no: Int)
        fun showCommentLoadingError()
        fun showComments(comments: List<Comment>)
    }
}