package com.maungedev.search.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.constant.ExtraNameConstant
import com.maungedev.eventtech.ui.adapter.EventLayoutAdapter
import com.maungedev.search.databinding.ActivitySearchBinding
import com.maungedev.search.di.searchModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModel()
    private lateinit var listAdapter: EventLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(searchModule)

        binding.apply {


            btnBack.setOnClickListener {
                onBackPressed()
            }
            val eventType: String = intent.getStringExtra(ExtraNameConstant.EVENT_TYPE) ?: "competition"


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String): Boolean {
                    return true
                }

                @SuppressLint("SetTextI18n")
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotEmpty()) {
                        if (eventType == "competition"){
                            viewModel.searchCompetition(newText).observe(this@SearchActivity, ::setSearchList)
                        }else{
                            viewModel.searchConference(newText).observe(this@SearchActivity, ::setSearchList)
                        }
                       
                    }
                    return true
                }
            })

        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSearchList(resource: Resource<List<Event>>?) {
        binding.apply {
            when (resource) {
                is Resource.Success -> {
                    loadingState(false)
                    resource.data?.let { listItem ->
                        tvResultCount.text = "Hasil Pencarian( ${listItem.size} )"
                        listAdapter = EventLayoutAdapter(this@SearchActivity)

                        isEmpty(listItem.isEmpty())

                        resource.data?.let { listAdapter.setItems(listItem) }
                        rvSearch.adapter = listAdapter
                        rvSearch.layoutManager = LinearLayoutManager(
                            this@SearchActivity,
                            LinearLayoutManager.VERTICAL, false
                        )
                    }
                }
                is Resource.Loading -> {
                    loadingState(true)
                }

                is Resource.Error -> {
                    loadingState(false)
                    Snackbar.make(
                        binding.root,
                        resource.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }
        }
    }

    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun isEmpty(state: Boolean){
        binding.apply {
            rvSearch.isVisible = !state
            layoutNotFound.isVisible = state
        }
    }
}