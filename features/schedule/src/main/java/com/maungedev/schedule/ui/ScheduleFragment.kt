package com.maungedev.schedule.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.ui.adapter.ScheduleAdapter
import com.maungedev.schedule.databinding.FragmentScheduleBinding
import com.maungedev.schedule.di.scheduleModule
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment : Fragment() {

    private val viewModel: ScheduleViewModel by viewModel()
    private var _binding: FragmentScheduleBinding? = null
    private lateinit var scheduleEventAdapter: ScheduleAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(scheduleModule)
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentUser().observe(viewLifecycleOwner,{
            it.data?.schedule.let { scheduleIds->
                if(scheduleIds!=null){
                    viewModel.getAllSchedule(scheduleIds).observe(viewLifecycleOwner,::setSchedule)
                }
            }
        })

        setCurrentDate()


    }

    @SuppressLint("SimpleDateFormat")
    private fun setCurrentDate() {
        val currentDate= SimpleDateFormat("d MMM, yyyy").format(Date())
        binding.tvCurrentDate.text = currentDate
    }

    private fun setSchedule(resource: Resource<List<Event>>?) {

        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                resource.data?.let { listItem ->
                    scheduleEventAdapter = ScheduleAdapter(requireContext())
                    scheduleEventAdapter.setItems(listItem)
                    binding.rvSchedule.adapter = scheduleEventAdapter
                    binding.rvSchedule.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                }
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_LONG)
                    .show()
            }

        }


    }

    private fun loadingState(b: Boolean) {

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}