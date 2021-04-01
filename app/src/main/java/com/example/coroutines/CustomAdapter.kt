package com.example.coroutines

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val context: Context,val dataList: List<DataModel>) :RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){

    class MyViewHolder(v:View):RecyclerView.ViewHolder(v) {
        val idTextView:TextView = v.findViewById(R.id.id_tv)
        val titleTextView:TextView = v.findViewById(R.id.title_tv)
        val bodyTextView:TextView = v.findViewById(R.id.body_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view , parent , false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.idTextView.text = dataList[position].postId.toString()
        holder.titleTextView.text = dataList[position].postTitle
        holder.bodyTextView.text = dataList[position].postBody
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}