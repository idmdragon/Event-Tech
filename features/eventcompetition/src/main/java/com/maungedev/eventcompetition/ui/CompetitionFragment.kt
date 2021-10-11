package com.maungedev.eventcompetition.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.domain.utils.Resource
import com.maungedev.eventcompetition.databinding.FragmentCompetitionBinding
import com.maungedev.eventcompetition.di.competitionModule
import com.maungedev.eventtech.ui.adapter.CompetitionCategoryAdapter
import com.maungedev.eventtech.ui.adapter.EventLayoutAdapter
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class CompetitionFragment : Fragment() {

    private val viewModel: CompetitionViewModel by viewModel()
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
        loadKoinModules(competitionModule)
        _binding = FragmentCompetitionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCompetitionCategory().observe(viewLifecycleOwner,::setCompetitionCategory)
    }

    private fun setCompetitionCategory(resource: Resource<List<CompetitionCategory>>?) {
        resource?.data.let {
            if (it != null) {
                categoryAdapter.setItems(it)
            }
            binding.rvEventCategory.adapter = categoryAdapter
            binding.rvEventCategory.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL, false
            )
        }
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