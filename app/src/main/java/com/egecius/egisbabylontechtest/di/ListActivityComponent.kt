package com.egecius.egisbabylontechtest.di

import com.egecius.egisbabylontechtest.postslist.ListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ListActivityModule::class])
interface ListActivityComponent {

    fun injectInto(listActivity: ListActivity)
}
