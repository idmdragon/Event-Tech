package com.maungedev.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.maungedev.authentication.R
import com.maungedev.authentication.databinding.FragmentRegisterBinding
import com.maungedev.eventtech.ui.main.MainActivity

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {

            binding.progressBar.isVisible = true
            binding.btnRegister.isEnabled = false
            Handler().postDelayed({
                startActivity(Intent(requireContext(), MainActivity::class.java)).also{
                    activity?.finish()
                }
            }, 1000)


        }

        binding.tvMasuk.setOnClickListener {
            val mLoginFragment = LoginFragment()
            val mFragmentManager = parentFragmentManager
           mFragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, mLoginFragment, LoginFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}