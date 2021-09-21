package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.maungedev.eventtech.databinding.ItemConferenceCategoryBinding
import com.maungedev.eventtech.ui.main.ui.conference.ConferenceCategory

class ConferenceCategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<ConferenceCategoryAdapter.ViewHolder>() {

    private val categories = arrayListOf<ConferenceCategory>()

    fun setItems(items: List<ConferenceCategory>) {
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
        fun bind(item: ConferenceCategory) {
            with(binding) {
                tvCategoryName.text = item.categoryName
                Glide.with(itemView.context)
                    .load(item.categoryIcon)
                    .into(ivCategoryIcon)
            }
        }
    }
}