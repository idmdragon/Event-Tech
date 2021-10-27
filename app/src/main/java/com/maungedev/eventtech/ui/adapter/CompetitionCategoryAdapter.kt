package com.maungedev.eventtech.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.eventtech.R
import com.maungedev.eventtech.databinding.ItemCompetitionCategoryBinding

class CompetitionCategoryAdapter() :
    RecyclerView.Adapter<CompetitionCategoryAdapter.ViewHolder>() {

    private val categories = arrayListOf<CompetitionCategory>()
    private var onItemSelectedListener: OnItemSelectedListener? = null
    private var activeCategory = 0

    fun setOnItemCallback(onItemSelected: (selectedCategory: CompetitionCategory) -> Unit) {
        onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(category: CompetitionCategory) {
                onItemSelected(category)
            }
        }
    }

    fun setItems(items: List<CompetitionCategory>) {
        this.categories.clear()
        this.categories.addAll(items)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CompetitionCategoryAdapter.ViewHolder {
        val itemBinding =
            ItemCompetitionCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CompetitionCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(categories[position],position)
        holder.itemView.setBackgroundResource(R.drawable.item_competition_selected)
        if (activeCategory == position){
            holder.itemView.setBackgroundResource(R.drawable.item_competition_selected)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.item_competition_unselected)
        }
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(private val binding: ItemCompetitionCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CompetitionCategory, position: Int) {
            itemView.setOnClickListener {
                activeCategory = position
                Log.d("category","klik")
                onItemSelectedListener?.onItemSelected(item)
                notifyDataSetChanged()

            }
            binding.tvEventCategory.text = item.categoryName
        }
    }
}



interface OnItemSelectedListener {
    fun onItemSelected(category: CompetitionCategory)
}