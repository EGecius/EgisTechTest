package com.egecius.egisbabylontechtest.features.showpostslist.di

import com.egecius.egisbabylontechtest.features.showpostslist.PostListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ListActivityModule::class])
interface ListActivityComponent {

    fun injectInto(postListActivity: PostListActivity)
}
