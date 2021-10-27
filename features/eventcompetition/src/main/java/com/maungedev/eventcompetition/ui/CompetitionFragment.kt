package com.maungedev.eventcompetition.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventcompetition.databinding.FragmentCompetitionBinding
import com.maungedev.eventcompetition.di.competitionModule
import com.maungedev.eventtech.constant.ExtraNameConstant
import com.maungedev.eventtech.constant.PageNameConstant
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
                viewModel.getEventsByCategories(it.categoryName).observe(viewLifecycleOwner,::setCompetitionEvent)
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

        viewModel.getAllCompetitionEvent().observe(viewLifecycleOwner,::setCompetitionEvent)
        viewModel.getCompetitionCategory().observe(viewLifecycleOwner,::setCompetitionCategory)
        binding.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), Class.forName(PageNameConstant.SEARCH_PAGE)).putExtra(
                ExtraNameConstant.EVENT_TYPE,"competition"
            ))
        }

    }

    private fun setCompetitionEvent(resource: Resource<List<Event>>) {
        when(resource){
            is Resource.Success -> {
                loadingState(false)
                competitionAdapter = EventLayoutAdapter(requireContext())
                resource.data?.let { competitionAdapter.setItems(it) }
                binding.rvCompetition.adapter = competitionAdapter
                binding.rvCompetition.layoutManager = LinearLayoutManager(
                    activity,
                    LinearLayoutManager.VERTICAL, false
                )
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root,resource.message.toString(), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun loadingState(b: Boolean) {

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


}