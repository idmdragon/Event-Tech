package com.maungedev.profile.ui.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.ExtraNameConstant.EMAIL
import com.maungedev.eventtech.constant.ExtraNameConstant.USERNAME
import com.maungedev.eventtech.constant.PageNameConstant.ABOUT_PAGE
import com.maungedev.eventtech.constant.PageNameConstant.AUTHENTICATION_PAGE
import com.maungedev.eventtech.constant.PageNameConstant.RESET_PASSWORD_PAGE
import com.maungedev.profile.databinding.FragmentProfileBinding
import com.maungedev.profile.ui.di.profileModule
import com.maungedev.profile.ui.ui.edit_username.EditUsernameActivity
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(profileModule)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrentUser().observe(viewLifecycleOwner, ::setProfileView)
        logout()
    }

    private fun setProfileView(resource: Resource<User>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)

                binding.apply {
                    resource.data.let { user ->

                        if (user != null) {
                            tvEmail.text = user.email
                            tvUsername.text = user.username

                            tvEditUsername.setOnClickListener {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        EditUsernameActivity::class.java
                                    ).putExtra(
                                        USERNAME, user.username
                                    )
                                )
                            }
                            tvEditPassword.setOnClickListener {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        Class.forName(RESET_PASSWORD_PAGE)
                                    ).putExtra(EMAIL, user.email)
                                )
                            }
                        }
                        tvAbout.setOnClickListener {
                            startActivity(
                                Intent(
                                    requireContext(),
                                    Class.forName(ABOUT_PAGE)
                                )
                            )
                        }
                    }
                }

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

    private fun logout() {
        binding.tvLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showLogoutDialog() {
        val materialBuilder = MaterialAlertDialogBuilder(requireContext()).create()
        val inflater: View =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_confirmation, null)
        val btnAccept: Button = inflater.findViewById(R.id.btn_accept)
        val btnCancel: Button = inflater.findViewById(R.id.btn_cancel)
        val title: TextView = inflater.findViewById(R.id.tv_dialog_title)
        val description: TextView = inflater.findViewById(R.id.tv_desc)

        description.text = getString(R.string.desc_logout)
        title.text = "Keluar dari aplikasi"
        btnAccept.setOnClickListener {
            materialBuilder.dismiss()
            viewModel.logout()

            requireContext().startActivity(
                Intent(
                    requireContext(),
                    Class.forName(AUTHENTICATION_PAGE)
                )
            ).also {
                activity?.finishAffinity()
            }
        }

        btnCancel.setOnClickListener {
            materialBuilder.dismiss()
        }

        materialBuilder.setView(inflater)
        materialBuilder.show()
    }

    private fun loadingState(state: Boolean) {
        binding.progressBar.isVisible = state
    }
}