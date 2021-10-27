package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maungedev.domain.model.Event
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_UID
import com.maungedev.eventtech.constant.PageNameConstant
import com.maungedev.eventtech.databinding.ItemListEventBinding
import com.maungedev.eventtech.utils.DateConverter

class EventLayoutAdapter (private val context: Context) :
    RecyclerView.Adapter<EventLayoutAdapter.ViewHolder>() {

    private val events = arrayListOf<Event>()

    fun setItems(items: List<Event>) {
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
        fun bind(item: Event) {
            with(binding) {
                tvEventTitle.text = item.eventName
                tvEventOrganizer.text = item.organizer
                val free = "Gratis"
               if(item.price.toString() == "0"){
                   tvEventPrice.text = free
               }else{
                   tvEventPrice.text = "Rp.${item.price}"
                }

                val dateAndMonth = DateConverter.convertMillisToString(item.date).split(" ").toTypedArray()
                val date = dateAndMonth[0]
                val month = dateAndMonth[1]
                tvEventDate.text = date
                tvEventMonth.text = month

                binding.btnDetail.setOnClickListener {
                    context.startActivity(Intent(itemView.context,Class.forName(PageNameConstant.DETAIL_PAGE)).putExtra(EVENT_UID,item.uid))
                }
                itemView.setOnClickListener {
                    context.startActivity(Intent(itemView.context,Class.forName(PageNameConstant.DETAIL_PAGE)).putExtra(EVENT_UID,item.uid))
                }

                Glide.with(itemView.context)
                    .load(item.eventCover)
                    .into(ivPoster)
            }
        }
    }
}