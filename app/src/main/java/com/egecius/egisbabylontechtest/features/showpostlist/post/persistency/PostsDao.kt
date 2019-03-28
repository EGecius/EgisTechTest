package com.egecius.egisbabylontechtest.features.showpostlist.post.persistency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.egecius.egisbabylontechtest.features.showpostlist.post.Post

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun loadAllPosts(): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(car: Post)

}