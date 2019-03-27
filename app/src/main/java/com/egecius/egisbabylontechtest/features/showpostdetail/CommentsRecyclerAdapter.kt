package com.egecius.egisbabylontechtest.features.showpostdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egecius.egisbabylontechtest.R
import com.egecius.egisbabylontechtest.features.showpostdetail.comments.Comment

class CommentsRecyclerAdapter : RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsViewHolder>() {

    private var postList: List<Comment> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CommentsRecyclerAdapter.CommentsViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.comments_list_item, viewGroup, false)
        return CommentsRecyclerAdapter.CommentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: CommentsRecyclerAdapter.CommentsViewHolder, i: Int) {
        val comment = postList[i]

        holder.title.text = comment.name
        holder.body.text = comment.body
    }

    fun setData(data: List<Comment>) {
        postList = data
        notifyDataSetChanged()
    }

    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var body: TextView = itemView.findViewById(R.id.body)
    }
}