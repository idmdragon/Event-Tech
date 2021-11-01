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
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_LINK_REGISTRATION
import com.maungedev.eventtech.constant.ExtraNameConstant.EVENT_UID
import com.maungedev.eventtech.utils.AlarmReceiver
import com.maungedev.eventtech.utils.DateConverter
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private val alarmReceiver = AlarmReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadKoinModules(detailModule)

        setUpObserver()

    }

    private fun setUpObserver() {
        val eventUID = intent.getStringExtra(EVENT_UID)

        if (eventUID != null) {
            viewModel.getEventById(eventUID).observe(this@DetailActivity, ::setDetailObserver)
            viewModel.getCurrentUser()
                .observe(this@DetailActivity, { setCurrentUser(it, eventUID) })

            viewModel.increaseNumbersOfView(eventUID).observe(this@DetailActivity, {})
        } else {
            Snackbar.make(
                binding.root,
                "Event yang anda cari tidak ditemukan",
                Snackbar.LENGTH_LONG
            ).show()
        }


        viewModel.isRemindered.observe(this@DetailActivity, ::stateReminder)

    }

    private fun setCurrentUser(resource: Resource<User>?, eventUID: String) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                resource.data?.let { user ->
                    viewModel.setReminderState(user.schedule.contains(eventUID))
                    viewModel.setFavoriteState(user.favorite.contains(eventUID))
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


    private fun setDetailObserver(resource: Resource<Event>?) {
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
                Snackbar.make(
                    binding.root,
                    resource.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setDetailView(event: Event) {
        buttonClickListener(event)
        with(binding) {
            tvEventTitle.text = event.eventName
            tvAbout.text = event.description
            tvPrerequisite.text = event.prerequisite
            tvEventInformation.text =
                "${DateConverter.convertMillisToDateMonth(event.date)} • ${event.location} • ${event.time}"
            val zero: Long = 0
            val price = when (event.price) {
                zero -> "Gratis"
                else -> "Rp. ${event.price}"
            }

            tvPrice.text = price
            Glide.with(this@DetailActivity)
                .load(event.eventCover)
                .transform(CenterCrop(), RoundedCorners(8))
                .apply(RequestOptions())
                .into(ivPoster)
        }
    }

    private fun buttonClickListener(data: Event) {
        binding.apply {

            btnRegistration.setOnClickListener {
                viewModel.increaseNumbersOfRegistrationClick(data.uid)
                    .observe(this@DetailActivity, {})
                startActivity(
                    Intent(this@DetailActivity, RegistrationActivity::class.java).putExtra(
                        EVENT_LINK_REGISTRATION, data.linkRegistration
                    )
                )
            }

            btnAddReminder.setOnClickListener {
                showReminderDialog(data)
            }

            btnBack.setOnClickListener {
                onBackPressed()
            }

            viewModel.isFavorited.observe(this@DetailActivity, { state ->
                stateFavorite(state)
                if (state) {
                    btnFavorite.setOnClickListener {
                        viewModel.deleteFavorite(data.uid)
                            .observe(this@DetailActivity, ::deleteFavoriteResponse)
                    }
                } else {
                    btnFavorite.setOnClickListener {
                        viewModel.addFavorite(data.uid)
                            .observe(this@DetailActivity, ::addFavoriteResponse)
                    }
                }
            })

        }
    }

    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
    }

    @SuppressLint("SetTextI18n")
    private fun stateReminder(state: Boolean) {
        binding.apply {
            if (state) {
                btnAddReminder.text = "Diingatkan"
                btnAddReminder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_checklist, 0, 0, 0
                )
            } else {
                btnAddReminder.text = "Pengingat"
                btnAddReminder.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_add, 0, 0, 0
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun stateFavorite(state: Boolean) {
        if (state) {
            binding.btnFavorite.setImageResource(
                R.drawable.ic_favorite_active
            )
        } else {
            binding.btnFavorite.setImageResource(
                R.drawable.ic_favorite_detail
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showReminderDialog(data: Event) {
        val materialBuilder = MaterialAlertDialogBuilder(this).create()
        val inflater: View = LayoutInflater.from(this).inflate(R.layout.dialog_confirmation, null)
        val btnAddSchedule: Button = inflater.findViewById(R.id.btn_accept)
        val btnCancel: Button = inflater.findViewById(R.id.btn_cancel)
        val reminderTitle: TextView = inflater.findViewById(R.id.tv_dialog_title)
        val reminderDescription: TextView = inflater.findViewById(R.id.tv_desc)

        viewModel.isRemindered.observe(this@DetailActivity, { state ->
            if (state) {
                reminderDescription.text =
                    getString(R.string.desc_reminder_dialog_delete, data.eventName)
                reminderTitle.text = "Hapus Pengingat Event"
                btnAddSchedule.setOnClickListener {
                    materialBuilder.dismiss()
                    viewModel.deleteSchedule(data.uid).observe(this, {
                        deleteScheduleResponse(it, data)
                    })
                }
            } else {
                reminderDescription.text = getString(R.string.desc_reminder_dialog, data.eventName)
                reminderTitle.text = "Tambahkan Pengingat Event"
                btnAddSchedule.setOnClickListener {
                    materialBuilder.dismiss()
                    viewModel.addSchedule(data.uid).observe(this, {
                        addScheduleResponse(it, data)
                    })
                }
            }
        })

        btnCancel.setOnClickListener {
            materialBuilder.dismiss()
        }

        materialBuilder.setView(inflater)
        materialBuilder.show()
    }

    private fun addScheduleResponse(resource: Resource<Unit>?, data: Event) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                alarmReceiver.setOneTimeAlarm(
                    this@DetailActivity,
                    DateConverter.convertMillisToStringForNotification(data.date),
                    data.time,
                    data.eventName,
                    data.uid
                )
                Snackbar.make(
                    binding.root,
                    "Event berhasil di tambahkan pengingat",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_SHORT)
                    .show()
            }

        }

    }

    private fun deleteScheduleResponse(resource: Resource<Unit>?, data: Event) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                Snackbar.make(
                    binding.root,
                    "Event berhasil dihapus dari pengingat",
                    Snackbar.LENGTH_LONG
                ).show()
                alarmReceiver.cancelAlarm(
                    this@DetailActivity,
                    DateConverter.convertMillisToStringForNotification(data.date),
                    data.time
                )
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_SHORT)
                    .show()
            }

        }

    }

    private fun addFavoriteResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                Snackbar.make(
                    binding.root,
                    "Event berhasil di tambahkan ke favorit",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun deleteFavoriteResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                Snackbar.make(
                    binding.root,
                    "Event berhasil dihapus dari favorit",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }
            is Resource.Loading -> {
                loadingState(true)
            }

            is Resource.Error -> {
                loadingState(false)
                Snackbar.make(binding.root, resource.message.toString(), Snackbar.LENGTH_SHORT)
                    .show()
            }

        }
    }
}
