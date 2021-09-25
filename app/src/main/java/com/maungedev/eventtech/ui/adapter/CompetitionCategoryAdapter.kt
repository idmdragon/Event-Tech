package com.maungedev.eventtech.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maungedev.domain.model.EventCompetitionCategory
import com.maungedev.eventtech.R
import com.maungedev.eventtech.databinding.ItemCompetitionCategoryBinding

class CompetitionCategoryAdapter() :
    RecyclerView.Adapter<CompetitionCategoryAdapter.ViewHolder>() {

    private val categories = arrayListOf<EventCompetitionCategory>()
    private var onItemSelectedListener: OnItemSelectedListener? = null


    fun setOnItemCallback(onItemSelected: (selectedCategory: EventCompetitionCategory) -> Unit) {
        onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(category: EventCompetitionCategory) {
                onItemSelected(category)
            }
        }
    }

    fun setItems(items: List<EventCompetitionCategory>) {
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
            holder.bind(categories[position])
    }

    override fun getItemCount() = categories.size

    inner class ViewHolder(private val binding: ItemCompetitionCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventCompetitionCategory) {
            if(item.categoryName.equals("Semua")){
                binding.layoutRoot.setBackgroundResource(R.drawable.item_competition_selected)
            }
            binding.tvEventCategory.text = item.categoryName
        }
    }
}

interface OnItemSelectedListener {
    fun onItemSelected(category: EventCompetitionCategory)
}