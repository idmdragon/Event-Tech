package com.maungedev.eventtech.ui.main.ui.conference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.databinding.FragmentConferenceBinding
import com.maungedev.eventtech.ui.adapter.ConferenceCategoryAdapter
import com.maungedev.eventtech.ui.adapter.MiniEventAdapter

class ConferenceFragment : Fragment() {

    private lateinit var conferenceViewModel: ConferenceViewModel
    private var _binding: FragmentConferenceBinding? = null
    private lateinit var popularEventAdapter: MiniEventAdapter
    private lateinit var categoryEventAdapter: ConferenceCategoryAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        conferenceViewModel =
            ViewModelProvider(this).get(ConferenceViewModel::class.java)

        _binding = FragmentConferenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        conferenceViewModel.getPopularEvent().observe(viewLifecycleOwner,::setPopularEvent)
        conferenceViewModel.getConferenceCategory().observe(viewLifecycleOwner,::setConferenceCategory)
    }

    private fun setConferenceCategory(list: List<ConferenceCategory>) {
        categoryEventAdapter = ConferenceCategoryAdapter(requireContext())
        categoryEventAdapter.setItems(list)
        binding.rvCategory.adapter = categoryEventAdapter
        binding.rvCategory.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun setPopularEvent(list: List<EventIT>) {
        popularEventAdapter = MiniEventAdapter(requireContext())
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