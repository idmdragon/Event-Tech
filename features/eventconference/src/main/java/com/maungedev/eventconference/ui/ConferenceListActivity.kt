package com.maungedev.eventconference.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.EventIT
import com.maungedev.eventconference.databinding.ActivityConferenceListBinding
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_CATEGORY
import com.maungedev.eventtech.ui.adapter.EventLayoutAdapter

class ConferenceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConferenceListBinding
    private lateinit var listAdapter: EventLayoutAdapter
    private val viewModel: ConferenceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConferenceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getPopularEvent().observe(this, ::setListEvent)

        with(binding){
            tvCategoryName.text = intent.getStringExtra(EVENT_CATEGORY)
            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun setListEvent(list: List<EventIT>) {
        with(binding) {
            listAdapter = EventLayoutAdapter(this@ConferenceListActivity)
            listAdapter.setItems(list)
            rvListConference.adapter = listAdapter
            rvListConference.layoutManager = LinearLayoutManager(
                this@ConferenceListActivity,
                LinearLayoutManager.VERTICAL, false
            )
        }

    }
}