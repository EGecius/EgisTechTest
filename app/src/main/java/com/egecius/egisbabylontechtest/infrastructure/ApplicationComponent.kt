package com.egecius.egisbabylontechtest.infrastructure

import com.egecius.egisbabylontechtest.features.showpostdetail.di.PostDetailComponent
import com.egecius.egisbabylontechtest.features.showpostdetail.di.PostDetailModule
import com.egecius.egisbabylontechtest.features.showpostslist.di.ListActivityComponent
import com.egecius.egisbabylontechtest.features.showpostslist.di.ListActivityModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun plus(listActivityModule: ListActivityModule): ListActivityComponent

    fun plus(postDetailModule: PostDetailModule): PostDetailComponent
}