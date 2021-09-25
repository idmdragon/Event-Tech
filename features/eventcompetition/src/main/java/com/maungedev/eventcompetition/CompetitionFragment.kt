package com.maungedev.eventcompetition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.EventCompetitionCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.eventcompetition.databinding.FragmentCompetitionBinding
import com.maungedev.eventtech.ui.adapter.CompetitionCategoryAdapter
import com.maungedev.eventtech.ui.adapter.EventLayoutAdapter

class CompetitionFragment : Fragment() {

    private lateinit var viewModel: CompetitionViewModel
    private var _binding: FragmentCompetitionBinding? = null
    private val binding get() = _binding!!

    private lateinit var competitionAdapter: EventLayoutAdapter
    private val categoryAdapter:CompetitionCategoryAdapter by lazy {
        CompetitionCategoryAdapter().apply {
            setOnItemCallback {
//                viewModel.filterPostByGameType(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(CompetitionViewModel::class.java)

        _binding = FragmentCompetitionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCompetitionCategory().observe(viewLifecycleOwner,::setCompetitionCategory)
        viewModel.getCompetitionEvent().observe(viewLifecycleOwner,::setCompetitionEvent)

    }

    private fun setCompetitionCategory(list: List<EventCompetitionCategory>) {
/*
        categoryAdapter = CompetitionCategoryAdapter()
*/
        categoryAdapter.setItems(list)
        binding.rvEventCategory.adapter = categoryAdapter
        binding.rvEventCategory.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
/*        categoryAdapter.setOnItemCallback(
            object : CompetitionCategoryAdapter.OnItemClickCallback {
                override fun onItemClicked(category: EventCompetitionCategory) {
                    Toast.makeText(requireContext(),"Category ${category.categoryName}",Toast.LENGTH_SHORT).show()
                }
            }
        )*/
    }

    private fun setCompetitionEvent(list: List<EventIT>) {
        competitionAdapter = EventLayoutAdapter(requireContext())
        competitionAdapter.setItems(list)
        binding.rvCompetition.adapter = competitionAdapter
        binding.rvCompetition.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }
}