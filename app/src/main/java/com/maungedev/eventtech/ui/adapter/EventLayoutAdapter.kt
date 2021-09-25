package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.databinding.ItemListEventBinding

class EventLayoutAdapter (private val context: Context) :
    RecyclerView.Adapter<EventLayoutAdapter.ViewHolder>() {

    private val events = arrayListOf<EventIT>()

    fun setItems(items: List<EventIT>) {
        this.events.clear()
        this.events.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventLayoutAdapter.ViewHolder {
        val itemBinding =
            ItemListEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EventLayoutAdapter.ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    inner class ViewHolder(private val binding: ItemListEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventIT) {
            with(binding) {
                tvEventTitle.text = item.eventName
                tvEventOrganizer.text = item.organizer
                val free = "Gratis"
               if(item.price.equals(0)){
                   tvEventPrice.text = free
               }else{
                   tvEventPrice.text = "Rp.${item.price}"
                }
                tvEventDate.text = "04"
                tvEventMonth.text = "Aug"

                Glide.with(itemView.context)
                    .load(item.eventCover)
                    .into(ivPoster)
            }
        }
    }
}