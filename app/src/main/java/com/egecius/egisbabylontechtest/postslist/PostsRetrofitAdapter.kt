package com.egecius.egisbabylontechtest.postslist

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class PostsRetrofitAdapter {

    private val baseUrl = "http://jsonplaceholder.typicode.com/"

    fun setupRetrofit(): PostsService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createLoggingOkHttpClient())
            .build()
            .create(PostsService::class.java)
    }

    private fun createLoggingOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}

interface PostsService {

    @GET("posts")
    fun getPosts() : Single<List<PostJson>>
}