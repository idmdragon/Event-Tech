package com.maungedev.authentication.ui.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.maungedev.authentication.R
import com.maungedev.authentication.databinding.FragmentRegisterBinding
import com.maungedev.authentication.ui.di.authModule
import com.maungedev.domain.model.User
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.ui.main.MainActivity
import org.koin.core.context.loadKoinModules
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(authModule)
        with(binding) {

            activity.apply {

                btnRegister.setOnClickListener {

                    val email = tilEmail.editText?.text.toString()
                    val password = tilPassword.editText?.text.toString()
                    val username = tilUsername.editText?.text.toString()
                    viewModel.signUp(
                        email,
                        password,
                        user = User(
                            uid = "",
                            email = email,
                            username = username,
                            favorite = "",
                            schedule = ""
                        )
                    ).observe(viewLifecycleOwner,::signUpResponse)

                }

                tvMasuk.setOnClickListener {
                    val mLoginFragment = LoginFragment()
                    val mFragmentManager = parentFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(
                            R.id.frame_container,
                            mLoginFragment,
                            LoginFragment::class.java.simpleName
                        )
                        addToBackStack(null)
                        commit()
                    }
                }
            }
        }
    }

    private fun signUpResponse(resource: Resource<Unit>) {
        when(resource){
            is Resource.Success -> {
                startActivity(Intent(requireContext(), MainActivity::class.java)).also {
                    activity?.finish()
                }
            }
            is Resource.Loading -> {
                }

            is Resource.Error -> {
                Snackbar.make(binding.root,resource.message.toString(),Snackbar.LENGTH_LONG).show()

            }

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun loadingState() {
    }


}