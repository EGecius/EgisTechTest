package com.egecius.egisbabylontechtest.features.showpostdetail.di

import com.egecius.egisbabylontechtest.features.showpostdetail.PostDetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [PostDetailModule::class])
interface PostDetailComponent {

    fun injectInto(postDetailActivity: PostDetailActivity)
}