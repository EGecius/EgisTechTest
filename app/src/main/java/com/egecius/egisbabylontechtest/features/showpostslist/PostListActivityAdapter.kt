package com.egecius.egisbabylontechtest.features.showpostslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.features.showpostslist.post.Post

class PostListActivityAdapter(private val onClickListener: OnClickListener) :
    RecyclerView.Adapter<PostListActivityAdapter.PostViewHolder>() {

    private var postList: List<Post> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.post_list_item, viewGroup, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, i: Int) {
        val post = postList[i]

        holder.cardView.setOnClickListener {
            onClickListener.onClick(PostClick(post, holder.title))
        }
        holder.title.text = post.title
    }

    fun setData(data: List<Post>) {
        postList = data
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: View = itemView.findViewById(R.id.cardView)
        var title: TextView = itemView.findViewById(R.id.title)
    }

    interface OnClickListener {
        fun onClick(postClick: PostClick)
    }

}
