package com.egecius.egisbabylontechtest

import io.reactivex.Scheduler

interface InteractorSchedulers {

    fun getExecutionsScheduler(): Scheduler

    fun getPostExecutionScheduler(): Scheduler
}