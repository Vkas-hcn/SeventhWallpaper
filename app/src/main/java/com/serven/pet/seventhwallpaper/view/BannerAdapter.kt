package com.serven.pet.seventhwallpaper.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serven.pet.seventhwallpaper.App
import com.serven.pet.seventhwallpaper.R
import com.serven.pet.seventhwallpaper.set.SevenSetUtils

class BannerAdapter(private val images: List<String>) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }
    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val realPosition = position % images.size
        val imageUrl = images[realPosition]
        holder.imgItem.setImageResource(SevenSetUtils.getImgByName(imageUrl))
        holder.itemView.setOnClickListener {
            listener?.onItemClick(realPosition)
        }
    }

    override fun getItemCount(): Int {
        return if (images.size > 1) Int.MAX_VALUE else images.size
    }

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgItem: ImageView = itemView.findViewById(R.id.bannerImage)
    }
}
