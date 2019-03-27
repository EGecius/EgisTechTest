package com.egecius.egisbabylontechtest.infrastructure

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestInteractorSchedulers : InteractorSchedulers {

    override fun getExecutionsScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun getPostExecutionScheduler(): Scheduler {
        return Schedulers.trampoline()
    }
}