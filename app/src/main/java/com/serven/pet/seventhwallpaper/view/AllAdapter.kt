package com.serven.pet.seventhwallpaper.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.serven.pet.seventhwallpaper.App
import com.serven.pet.seventhwallpaper.R
import com.serven.pet.seventhwallpaper.set.SevenSetUtils

class AllAdapter (private var dataList: List<String>) :
    RecyclerView.Adapter<AllAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.all_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_all, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        Glide.with(App.appContext)
            .load(SevenSetUtils.getImgByName(data))
            .thumbnail(0.12f)
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
            .into(holder.imgItem)
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newData: List<String>) {
        dataList = newData
        notifyDataSetChanged()
    }
}