package com.example.redcarpettask.adapters

import Articles
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.redcarpettask.R
import java.util.zip.Inflater

class MainAdapter(
    val context: Context,
    val list: ArrayList<Articles>,
    val onmainItemClickListener: onMainItemClickListener
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headline: TextView = itemView.findViewById(R.id.headline)
        val mainimage: ImageView = itemView.findViewById(R.id.mainitemimage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_example_item, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val obj = list[position]
        holder.headline.text = obj.title
        Glide.with(context).load(obj.urlToImage).error(R.drawable.ic_launcher_background)
            .into(holder.mainimage)
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                onmainItemClickListener.onclick(obj)
            }
        }
    }

    public interface onMainItemClickListener {
        public fun onclick(articles: Articles)
    }
}