package com.egecius.egisbabylontechtest.di

import com.egecius.egisbabylontechtest.ListActivity
import dagger.Component

@Component(modules = [ListActivityModule::class])
interface ListActivityComponent {

    fun injectInto(listActivity: ListActivity)
}
