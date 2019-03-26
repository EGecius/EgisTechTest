package com.egecius.egisbabylontechtest.postdetail.di

import com.egecius.egisbabylontechtest.postdetail.PostDetailActivity
import dagger.Subcomponent

@Subcomponent(modules = [PostDetailModule::class])
interface PostDetailComponent {

    fun injectInto(postDetailActivity: PostDetailActivity)
}