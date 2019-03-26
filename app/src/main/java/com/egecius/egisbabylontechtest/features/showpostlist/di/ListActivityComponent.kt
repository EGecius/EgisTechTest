package com.egecius.egisbabylontechtest.features.showpostlist.di

import com.egecius.egisbabylontechtest.features.showpostlist.PostListActivity
import dagger.Subcomponent

@Subcomponent(modules = [ListActivityModule::class])
interface ListActivityComponent {

    fun injectInto(postListActivity: PostListActivity)
}
