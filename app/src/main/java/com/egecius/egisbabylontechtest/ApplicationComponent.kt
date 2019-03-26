package com.egecius.egisbabylontechtest

import com.egecius.egisbabylontechtest.postdetail.di.PostDetailComponent
import com.egecius.egisbabylontechtest.postdetail.di.PostDetailModule
import com.egecius.egisbabylontechtest.postslist.di.ListActivityComponent
import com.egecius.egisbabylontechtest.postslist.di.ListActivityModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun plus(listActivityModule: ListActivityModule): ListActivityComponent

    fun plus(postDetailModule: PostDetailModule): PostDetailComponent
}