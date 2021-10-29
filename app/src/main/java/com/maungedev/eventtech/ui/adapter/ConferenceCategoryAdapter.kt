package com.maungedev.eventtech.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_CATEGORY
import com.maungedev.eventtech.constant.PageNameConstant.CONFERENCE_LIST_PAGE
import com.maungedev.eventtech.databinding.ItemConferenceCategoryBinding

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
                    .placeholder(R.drawable.ic_category_placeholder)
                    .into(ivCategoryIcon)

                itemView.setOnClickListener {
                    context.startActivity(Intent(context,Class.forName(CONFERENCE_LIST_PAGE)).also{
                        it.putExtra(EVENT_CATEGORY,item.categoryName)
                    })
                }
            }
        }
    }
}