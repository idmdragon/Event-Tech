package com.maungedev.detail.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.maungedev.detail.databinding.ActivityDetailBinding
import com.maungedev.detail.di.detailModule
import com.maungedev.detail.ui.webview.RegistrationActivity
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_LINK_REGISTRATION
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_UID
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(detailModule)

        intent.getStringExtra(EVENT_UID).let {
            if (it != null) {
                viewModel.getEventById(it).observe(this@DetailActivity, ::setDetailObserver)
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun setDetailObserver(resource: Resource<Event>) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                resource.data?.let { setDetailView(it) }

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

    @SuppressLint("SetTextI18n")
    private fun setDetailView(data: Event) {
        with(binding) {
            data.let { event ->

                tvEventTitle.text = event.eventName
                tvAbout.text = event.description
                tvPrerequisite.text = event.prerequisite
                tvEventInformation.text = "${event.date} • ${event.location} • ${event.time}"
                val zero: Long = 0

                val price = when (event.price) {
                    zero -> "Gratis"
                    else -> "Rp. ${event.price}"
                }

                tvPrice.text = price
                Glide.with(this@DetailActivity)
                    .load(event.eventCover)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .placeholder(R.drawable.image_placeholder)
                    .apply(RequestOptions())
                    .into(ivPoster)


                btnRegistration.setOnClickListener {
                    startActivity(
                        Intent(this@DetailActivity, RegistrationActivity::class.java).putExtra(
                            EVENT_LINK_REGISTRATION, event.linkRegistration
                        )
                    )
                }

                btnAddReminder.setOnClickListener {
                    showDialog(data)
                }

                btnFavorite.setOnClickListener {
                    viewModel.addFavorite(data.uid).observe(this@DetailActivity,::addFavoriteResponse)
                }
            }

        }
    }



    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    private fun showDialog(data: Event) {
        val materialBuilder = MaterialAlertDialogBuilder(this).create()
        val inflater: View =
            LayoutInflater.from(this).inflate(R.layout.dialog_confirmation, null)

        val btnAddSchedule: Button = inflater.findViewById(R.id.btn_add_schedule)
        val btnCancel: Button = inflater.findViewById(R.id.btn_cancel)
        val reminderDescription: TextView = inflater.findViewById(R.id.tv_desc)

        reminderDescription.text = getString(R.string.desc_reminder_dialog, data.eventName)

        btnAddSchedule.setOnClickListener {
            materialBuilder.dismiss()
            viewModel.addSchedule(data.uid).observe(this,::addScheduleResponse)
        }
        btnCancel.setOnClickListener {
            materialBuilder.dismiss()
        }

        materialBuilder.setView(inflater)
        materialBuilder.show()
    }

    private fun addScheduleResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                Snackbar.make(binding.root, "Event berhasil di tambahkan pengingat", Snackbar.LENGTH_LONG)
                    .show()
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

    private fun addFavoriteResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                Snackbar.make(binding.root, "Event berhasil di tambahkan ke favorit", Snackbar.LENGTH_LONG)
                    .show()
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

}
