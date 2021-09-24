package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.R
import com.maungedev.eventtech.databinding.ItemScheduleBinding

class ScheduleAdapter(private val context: Context) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private val events = arrayListOf<EventIT>()
    private val color = MutableLiveData<Boolean>()

    fun setItems(items: List<EventIT>) {
        this.events.clear()
        this.events.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val itemBinding =
            ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
        holder.bind(events[position])
        color.value = position%2==0
    }

    override fun getItemCount() = events.size

    inner class ViewHolder(private val binding: ItemScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventIT) {
            with(binding) {
                if (color.value == true){
                    layoutEvent.setBackgroundResource(R.color.green)
                }else{
                    layoutEvent.setBackgroundResource(R.color.blue)
                }
                tvEventDate.text = "11"
                tvEventTitle.text = item.eventName
                tvEventMonth.text = "FEB"
                tvEventTime.text = "09.00 - 11.00"
            }
        }
    }
}
