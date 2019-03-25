package com.egecius.egisbabylontechtest

import com.egecius.egisbabylontechtest.di.ListActivityComponent
import com.egecius.egisbabylontechtest.di.ListActivityModule
import dagger.Component

@Component (modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun plus(listActivityModule: ListActivityModule) : ListActivityComponent
}