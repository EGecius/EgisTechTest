package com.egecius.egisbabylontechtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListActivityAdapter : RecyclerView.Adapter<ListActivityAdapter.PostViewHolder>() {

    private var postList : List<Post> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): PostViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.post_list_item, viewGroup, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, i: Int) {
        holder.title.text = postList[i].title
    }

    fun setData(data: List<Post>) {
        postList = data
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: View = itemView.findViewById(R.id.card_view)
        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)
    }

}
