package com.example.nasagallery.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.nasagallery.R
import com.example.nasagallery.network.LinksNasa
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.grid_cell.view.*

class OverviewAdapter(private val imageList: List<List<LinksNasa>>) :
    RecyclerView.Adapter<OverviewAdapter.OverviewHolder>() {
    class OverviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageLink: ImageView = itemView.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OverviewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_cell, parent, false)
        return OverviewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OverviewHolder, position: Int) {
        val currentItemList = imageList[position]
        if (currentItemList.isNotEmpty()) {
            val currentItem = currentItemList.first()
            Picasso.with(holder.imageLink.context)
                .load(currentItem.href)
                .into(holder.imageLink)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size

    }

}