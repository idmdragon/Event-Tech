package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.maungedev.domain.model.Event
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.ExtraNameConstant
import com.maungedev.eventtech.constant.PageNameConstant
import com.maungedev.eventtech.databinding.ItemScheduleBinding
import com.maungedev.eventtech.utils.DateConverter

class ScheduleAdapter(private val context: Context) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private val events = arrayListOf<Event>()
    private val color = MutableLiveData<Boolean>()

    fun setItems(items: List<Event>) {
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
        fun bind(item: Event) {
            with(binding) {
                if (color.value == true){
                    layoutEvent.setBackgroundResource(R.color.green)
                }else{
                    layoutEvent.setBackgroundResource(R.color.blue)
                }
                val titleSchedule = item.eventName + " - " +item.organizer
                tvEventTitle.text = titleSchedule


                val dateAndMonth = DateConverter.convertMillisToString(item.date).split(" ").toTypedArray()
                val date = dateAndMonth[0]
                val month = dateAndMonth[1]
                tvEventDate.text = date
                tvEventMonth.text = month
                tvEventTime.text = item.time

                btnDetail.setOnClickListener {
                    context.startActivity(
                        Intent(itemView.context,Class.forName(PageNameConstant.DETAIL_PAGE)).putExtra(
                        ExtraNameConstant.EVENT_UID,item.uid))
                }
            }
        }
    }
}
