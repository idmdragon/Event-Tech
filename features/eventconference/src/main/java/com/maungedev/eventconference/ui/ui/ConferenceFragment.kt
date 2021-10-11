package com.maungedev.eventconference.ui.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.domain.utils.Resource
import com.maungedev.eventconference.databinding.FragmentConferenceBinding
import com.maungedev.eventconference.ui.di.conferenceModule
import com.maungedev.eventtech.constant.PageNameConstant.SEARCH_PAGE
import com.maungedev.eventtech.ui.adapter.ConferenceCategoryAdapter
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class ConferenceFragment : Fragment() {

    private val conferenceViewModel: ConferenceViewModel by viewModel()
    private var _binding: FragmentConferenceBinding? = null
    private lateinit var popularEventAdapter: MiniLayoutAdapter
    private lateinit var categoryEventAdapter: ConferenceCategoryAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(conferenceModule)

        _binding = FragmentConferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        conferenceViewModel.getPopularEvent().observe(viewLifecycleOwner,::setPopularEvent)
        conferenceViewModel.getConferenceCategory().observe(viewLifecycleOwner,::setConferenceCategory)
        binding.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), Class.forName(SEARCH_PAGE)))
        }
    }

    private fun setConferenceCategory(resource: Resource<List<ConferenceCategory>>?) {
        resource?.data?.let {
            categoryEventAdapter = ConferenceCategoryAdapter(requireContext())
            categoryEventAdapter.setItems(it)
            binding.rvCategory.adapter = categoryEventAdapter
            binding.rvCategory.layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL, false
            )
        }

    }


    private fun setPopularEvent(list: List<EventIT>) {
        popularEventAdapter = MiniLayoutAdapter(requireContext())
        popularEventAdapter.setItems(list)
        binding.rvPopular.adapter = popularEventAdapter
        binding.rvPopular.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}