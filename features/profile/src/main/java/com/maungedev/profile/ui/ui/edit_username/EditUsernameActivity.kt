package com.maungedev.profile.ui.ui.edit_username

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.constant.ExtraNameConstant.USERNAME
import com.maungedev.profile.databinding.ActivityEditUsernameBinding
import com.maungedev.profile.ui.di.profileModule
import com.maungedev.profile.ui.ui.profile.ProfileViewModel
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel
class EditUsernameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditUsernameBinding
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(profileModule)
        binding = ActivityEditUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            tilEditUsername.editText?.setText(intent.getStringExtra(USERNAME))

            btnBack.setOnClickListener {
                onBackPressed()
            }

            btnUpdate.setOnClickListener {
                viewModel.updateUsername(tilEditUsername.editText?.text.toString())
                    .observe(this@EditUsernameActivity, ::editUsernameResponse)
            }
        }

    }

    private fun editUsernameResponse(resource: Resource<Unit>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                onBackPressed()
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
        with(binding) {
            progressBar.isVisible = state
            btnUpdate.isEnabled = !state
        }
    }
}