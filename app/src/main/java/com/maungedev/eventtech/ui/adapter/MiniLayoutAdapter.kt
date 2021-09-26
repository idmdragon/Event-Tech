package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.constant.PageNameConstant.DETAIL_PAGE
import com.maungedev.eventtech.databinding.ItemMiniEventBinding

class MiniLayoutAdapter(private val context: Context) :
    RecyclerView.Adapter<MiniLayoutAdapter.ViewHolder>() {

    private val events = arrayListOf<EventIT>()

    fun setItems(items: List<EventIT>) {
        this.events.clear()
        this.events.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniLayoutAdapter.ViewHolder {
        val itemBinding =
            ItemMiniEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MiniLayoutAdapter.ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    inner class ViewHolder(private val binding: ItemMiniEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventIT) {
            with(binding) {
                tvEventCategory.text = item.eventCategory
                tvEventTitle.text = item.eventName
                tvEventDate.text = item.date
                Glide.with(itemView.context)
                    .load(item.eventCover)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .placeholder(ColorDrawable(Color.CYAN))
                    .apply(RequestOptions())
                    .into(ivPoster)

                itemView.setOnClickListener {
                    context.startActivity(Intent(itemView.context,Class.forName(DETAIL_PAGE)))
                }
            }
        }
    }
}