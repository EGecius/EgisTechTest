package com.egecius.egisbabylontechtest

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

class ListActivityPresenter(private val getPostsInteractor: GetPostsInteractor) {

    private val compositeDisposable = CompositeDisposable()

    fun startPresenting(view: View) {
        val disposable = getPostsInteractor.getPosts()
            .subscribe(Consumer {
            }, Consumer {
            })

        compositeDisposable.add(disposable)
    }

    fun stopPresenting() {
        compositeDisposable.clear()
    }

    interface View {

    }
}