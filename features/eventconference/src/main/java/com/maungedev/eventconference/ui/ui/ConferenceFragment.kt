package com.maungedev.eventconference.ui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.ConferenceCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventconference.databinding.FragmentConferenceBinding
import com.maungedev.eventconference.ui.di.conferenceModule
import com.maungedev.eventtech.constant.ExtraNameConstant
import com.maungedev.eventtech.constant.PageNameConstant
import com.maungedev.eventtech.constant.PageNameConstant.SEARCH_PAGE
import com.maungedev.eventtech.ui.adapter.ConferenceCategoryAdapter
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class ConferenceFragment : Fragment() {

    private val viewModel: ConferenceViewModel by viewModel()
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
        viewModel.getConferenceCategory()
            .observe(viewLifecycleOwner, ::setConferenceCategory)

        viewModel.getAllPopularEvent().observe(viewLifecycleOwner, ::setPopularEvent)

        binding.btnSearch.setOnClickListener {
            startActivity(
                Intent(requireContext(), Class.forName(SEARCH_PAGE)).putExtra(
                    ExtraNameConstant.EVENT_TYPE, "conference"
                )
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshAllEvent().observe(viewLifecycleOwner, ::refreshResponse)
        }

    }

    private fun refreshResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                binding.swipeRefresh.isEnabled = true
                binding.swipeRefresh.isRefreshing = false
            }
            is Resource.Loading -> {
                loadingState(true)
                binding.swipeRefresh.isEnabled = false
            }

            is Resource.Error -> {
                binding.swipeRefresh.isEnabled = true
                binding.swipeRefresh.isRefreshing = false
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }

    private fun setPopularEvent(resource: Resource<List<Event>>) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                popularEventAdapter = MiniLayoutAdapter(requireContext())
                resource.data?.let {
                    val listTopFourPopularEvent = ArrayList<Event>()
                    listTopFourPopularEvent.addAll(it)
                    popularEventAdapter.setItems(listTopFourPopularEvent.take(4))
                }

                binding.btnAll.setOnClickListener {
                    startActivity(
                        Intent(
                            requireContext(),
                            Class.forName(PageNameConstant.CONFERENCE_LIST_PAGE)
                        ).also {
                            it.putExtra(ExtraNameConstant.EVENT_CATEGORY, "Popular")
                        })
                }

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
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }


    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}