package com.maungedev.eventconference.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventconference.databinding.ActivityConferenceListBinding
import com.maungedev.eventconference.ui.di.conferenceModule
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_CATEGORY
import com.maungedev.eventtech.ui.adapter.EventLayoutAdapter
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter
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
            tvCategoryName.text = categories

            if (categories == "Semua") {
                viewModel.getAllConferenceEvent()
                    .observe(this@ConferenceListActivity, ::setListEvent)
            } else {
                viewModel.getEventByCategories(categories)
                    .observe(this@ConferenceListActivity, ::setListEvent)
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setListEvent(resource: Resource<List<Event>>) {
        when (resource) {
            is Resource.Success -> {
                with(binding) {
                    listAdapter = EventLayoutAdapter(this@ConferenceListActivity)
                    resource.data?.let { listAdapter.setItems(it) }
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

    private fun loadingState(b: Boolean) {

    }

}