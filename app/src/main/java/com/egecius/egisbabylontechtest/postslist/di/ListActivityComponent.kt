package com.egecius.egisbabylontechtest.postslist.di

import com.egecius.egisbabylontechtest.postslist.PostsListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ListActivityModule::class])
interface ListActivityComponent {

    fun injectInto(postsListActivity: PostsListActivity)
}