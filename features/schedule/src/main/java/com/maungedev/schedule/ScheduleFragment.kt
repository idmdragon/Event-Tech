package com.maungedev.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.Event
import com.maungedev.eventtech.ui.adapter.ScheduleAdapter
import com.maungedev.schedule.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private lateinit var viewModel: ScheduleViewModel
    private var _binding: FragmentScheduleBinding? = null
    private lateinit var scheduleEventAdapter: ScheduleAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(ScheduleViewModel::class.java)
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getScheduleEvent().observe(viewLifecycleOwner,::setSchedule)

    }

    private fun setSchedule(list: List<Event>) {
        scheduleEventAdapter = ScheduleAdapter(requireContext())
        scheduleEventAdapter.setItems(list)
        binding.rvSchedule.adapter = scheduleEventAdapter
        binding.rvSchedule.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}