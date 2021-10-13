package com.maungedev.eventconference.ui.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
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
        conferenceViewModel.getConferenceCategory().observe(viewLifecycleOwner,::setConferenceCategory)
        conferenceViewModel.getAllConferenceEvent().observe(viewLifecycleOwner,::setAllConference)
        binding.btnSearch.setOnClickListener {
            startActivity(Intent(requireContext(), Class.forName(SEARCH_PAGE)))
        }
    }

    private fun setAllConference(resource: Resource<List<Event>>) {
        when(resource){
            is Resource.Success -> {
                loadingState(false)
                popularEventAdapter = MiniLayoutAdapter(requireContext())
                resource.data?.let { popularEventAdapter.setItems(it) }
                binding.rvPopular.adapter = popularEventAdapter
                binding.rvPopular.layoutManager = LinearLayoutManager(
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


    private fun setPopularEvent(list: List<Event>) {
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