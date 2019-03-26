package com.egecius.egisbabylontechtest.postslist

import com.egecius.egisbabylontechtest.BuildConfig.DEBUG
import com.egecius.egisbabylontechtest.postdetail.comments.CommentJson
import com.egecius.egisbabylontechtest.postdetail.user.UserJson
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class PostsRetrofitAdapter {

    private val baseUrl = "http://jsonplaceholder.typicode.com/"

    fun setupRetrofit(): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createLoggingOkHttpClient())
            .build()
            .create(NetworkService::class.java)
    }

    private fun createLoggingOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        if (DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}

interface NetworkService {

    @GET("posts")
    fun getPosts(): Single<List<PostJson>>


    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Single<UserJson>

    @GET("comments")
    fun getComments(): Single<List<CommentJson>>
}