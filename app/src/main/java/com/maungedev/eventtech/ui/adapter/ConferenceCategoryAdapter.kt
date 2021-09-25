package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maungedev.domain.model.EventConferenceCategory
import com.maungedev.eventtech.databinding.ItemConferenceCategoryBinding

class ConferenceCategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<ConferenceCategoryAdapter.ViewHolder>() {

    private val categories = arrayListOf<EventConferenceCategory>()

    fun setItems(items: List<EventConferenceCategory>) {
        this.categories.clear()
        this.categories.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConferenceCategoryAdapter.ViewHolder {
        val itemBinding =
            ItemConferenceCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ConferenceCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(private val binding: ItemConferenceCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventConferenceCategory) {
            with(binding) {
                tvCategoryName.text = item.categoryName
                Glide.with(itemView.context)
                    .load(item.categoryIcon)
                    .into(ivCategoryIcon)
            }
        }
    }
}