package com.maungedev.eventcompetition.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.CompetitionCategory
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventcompetition.R
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
    private val categoryAdapter: CompetitionCategoryAdapter by lazy {
        CompetitionCategoryAdapter().apply {
            setOnItemCallback {
                viewModel.sortType.observe(viewLifecycleOwner, { sortBy ->
                    viewModel.getEventsByCategories(it.categoryName).observe(viewLifecycleOwner, {
                        setCompetitionEvent(it, sortBy)
                    })
                })
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

        viewModel.setSortType("Semua")

        viewModel.sortType.observe(viewLifecycleOwner, { sortBy ->
            viewModel.getAllCompetitionEvent().observe(viewLifecycleOwner, {
                setCompetitionEvent(it, sortBy)
            })
        })

        viewModel.getCompetitionCategory().observe(viewLifecycleOwner, ::setCompetitionCategory)

        binding.btnSearch.setOnClickListener {
            startActivity(
                Intent(requireContext(), Class.forName(PageNameConstant.SEARCH_PAGE)).putExtra(
                    ExtraNameConstant.EVENT_TYPE, "competition"
                )
            )
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshAllEvent().observe(viewLifecycleOwner, ::refreshResponse)
        }
        binding.btnMenu.setOnClickListener {
            showFilterMenu(it, R.menu.filter_menu, requireContext())
        }
    }

    private fun refreshResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                binding.swipeRefresh.isRefreshing = false
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                binding.swipeRefresh.isRefreshing = false
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_LONG)
                    .show()
            }
        }

    }

    private fun setCompetitionEvent(resource: Resource<List<Event>>, sortType: String) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                competitionAdapter = EventLayoutAdapter(requireContext())
                resource.data?.let {
                    val sortedList = ArrayList<Event>()
                    sortedList.addAll(it)
                    when (sortType) {
                        "Urutkan Berdasarkan Biaya" -> {
                            sortedList.sortBy { event: Event ->
                                event.price
                            }
                            competitionAdapter.setItems(sortedList)
                        }
                        "Urutkan Berdasarkan Tanggal" -> {
                            sortedList.sortBy { event: Event ->
                                event.date
                            }
                            competitionAdapter.setItems(sortedList)
                        }
                        "Urutkan Berdasarkan Popularitas" -> {
                            sortedList.sortByDescending { event: Event ->
                                event.numbersOfView
                            }
                            competitionAdapter.setItems(sortedList)
                        }

                        else -> {
                            competitionAdapter.setItems(sortedList)
                        }
                    }

                }
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
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
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

    private fun showFilterMenu(
        view: View,
        optionMenu: Int,
        context: Context
    ) {
        val popup = PopupMenu(context, view)
        popup.menuInflater.inflate(optionMenu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.sort_by_price -> {
                    viewModel.setSortType(getString(com.maungedev.eventtech.R.string.text_sort_by_price))
                }
                R.id.sort_by_date -> {
                    viewModel.setSortType(getString(com.maungedev.eventtech.R.string.text_sort_by_date))
                }
                R.id.sort_by_popularity -> {
                    viewModel.setSortType(getString(com.maungedev.eventtech.R.string.text_sort_by_popularity))
                }
                else -> {
                    viewModel.setSortType(getString(com.maungedev.eventtech.R.string.text_all))
                }
            }
            true

        }
        popup.setOnDismissListener {}
        popup.show()

    }

}