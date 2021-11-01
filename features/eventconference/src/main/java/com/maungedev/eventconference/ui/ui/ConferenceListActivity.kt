package com.maungedev.eventconference.ui.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventconference.R
import com.maungedev.eventconference.databinding.ActivityConferenceListBinding
import com.maungedev.eventconference.ui.di.conferenceModule
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_CATEGORY
import com.maungedev.eventtech.ui.adapter.EventLayoutAdapter
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class ConferenceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConferenceListBinding
    private lateinit var listAdapter: EventLayoutAdapter
    private val viewModel: ConferenceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(conferenceModule)
        binding = ActivityConferenceListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {

            val categories: String = intent.getStringExtra(EVENT_CATEGORY) ?: "Semua"
            viewModel.setSortType("Semua")

            tvCategoryName.text = categories

            viewModel.sortType.observe(this@ConferenceListActivity, { sortBy ->

                when (categories) {
                    "Semua" -> {
                        viewModel.getAllConferenceEvent()
                            .observe(this@ConferenceListActivity, {
                                setListEvent(it, sortBy)
                            })
                    }
                    "Popular" -> {
                        viewModel.getAllPopularEvent()
                            .observe(this@ConferenceListActivity, {
                                setListEvent(it, sortBy)
                            })
                    }
                    else -> {
                        viewModel.getEventByCategories(categories)
                            .observe(this@ConferenceListActivity, {
                                setListEvent(it, sortBy)
                            })
                    }
                }
            })

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnMenu.setOnClickListener {
                showFilterMenu(it, R.menu.filter_menu_conference, this@ConferenceListActivity)
            }


        }
    }

    private fun setListEvent(resource: Resource<List<Event>>, sortType: String) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                with(binding) {
                    listAdapter = EventLayoutAdapter(this@ConferenceListActivity)
                    resource.data?.let {
                        val sortedList = ArrayList<Event>()
                        sortedList.addAll(it)
                        when (sortType) {
                            "Urutkan Berdasarkan Biaya" -> {
                                sortedList.sortBy { event: Event ->
                                    event.price
                                }
                                listAdapter.setItems(sortedList)
                            }
                            "Urutkan Berdasarkan Tanggal" -> {
                                sortedList.sortBy { event: Event ->
                                    event.date
                                }
                                listAdapter.setItems(sortedList)
                            }
                            "Urutkan Berdasarkan Popularitas" -> {
                                sortedList.sortByDescending { event: Event ->
                                    event.numbersOfView
                                }
                                listAdapter.setItems(sortedList)
                            }

                            else -> {
                                listAdapter.setItems(sortedList)
                            }
                        }
                    }
                    rvListConference.adapter = listAdapter
                    rvListConference.layoutManager = LinearLayoutManager(
                        this@ConferenceListActivity,
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

    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
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
                R.id.sort_by_popularity ->{
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