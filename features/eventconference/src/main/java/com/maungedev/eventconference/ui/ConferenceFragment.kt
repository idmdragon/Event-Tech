package com.maungedev.eventconference.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.EventIT
import com.maungedev.eventconference.databinding.FragmentConferenceBinding
import com.maungedev.eventtech.ui.adapter.ConferenceCategoryAdapter
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter

class ConferenceFragment : Fragment() {

    private lateinit var conferenceViewModel: ConferenceViewModel
    private var _binding: FragmentConferenceBinding? = null
    private lateinit var popularEventAdapter: MiniLayoutAdapter
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

        binding.btnSearch.setOnClickListener {
            Toast.makeText(requireContext(),"Search Button",Toast.LENGTH_SHORT).show()
        }
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