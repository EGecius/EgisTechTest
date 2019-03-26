package com.egecius.egisbabylontechtest.features.showpostslist.di

import com.egecius.egisbabylontechtest.features.showpostslist.PostsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ListActivityModule::class])
interface ListActivityComponent {

    fun injectInto(postsListActivity: PostsListActivity)
}
