package com.egecius.egisbabylontechtest.infrastructure

import io.reactivex.Scheduler

interface InteractorSchedulers {

    fun getExecutionsScheduler(): Scheduler

    fun getPostExecutionScheduler(): Scheduler
}