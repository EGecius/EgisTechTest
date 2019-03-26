package com.egecius.egisbabylontechtest.features.showpostlist.di

import com.egecius.egisbabylontechtest.infrastructure.InteractorSchedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidInteractorSchedulers : InteractorSchedulers {

    override fun getExecutionsScheduler(): Scheduler {
        return Schedulers.io()
    }

    override fun getPostExecutionScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}
