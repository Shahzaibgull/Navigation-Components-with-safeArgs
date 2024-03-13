package com.example.shahzaibdevelopertask.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shahzaibdevelopertask.databinding.ListItemBinding


class RecyclerView_Adapter( var list: List<JsonApiResponse_DataClass>,var context: Context, val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView_Adapter.MyViewHolder>()  {

    interface OnItemClickListener {
        fun onItemClick(jsonapiresponseDataclass: JsonApiResponse_DataClass)
    }

    inner class MyViewHolder(var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView_Adapter.MyViewHolder {
        var bindingView=ListItemBinding.inflate(LayoutInflater.from(context),parent,false)  //New method - use- Binding
        return MyViewHolder(bindingView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView_Adapter.MyViewHolder, position: Int) {
        val currentItem=list[position]

        Glide.with(context).load(currentItem.thumbnailUrl).into(holder.binding.thumbnailImage)
        holder.binding.titleTextView.text=currentItem.title

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}