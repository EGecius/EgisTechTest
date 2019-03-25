package com.egecius.egisbabylontechtest

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