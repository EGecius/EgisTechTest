package com.egecius.egisbabylontechtest.features.showpostlist.post.persistency

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostsDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {

        @Volatile
        private var INSTANCE: PostsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PostsDatabase {

            val snapshot = INSTANCE

            return if (snapshot != null) {
                snapshot
            } else {
                val newInstance = Room.databaseBuilder(context, PostsDatabase::class.java, "Sample.db").build()
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}