package com.maungedev.authentication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maungedev.authentication.databinding.FragmentRegisterBinding
import com.maungedev.eventtech.R
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
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                activity?.finish()
            }
        }

        binding.tvMasuk.setOnClickListener {
            val mLoginFragment = LoginFragment()
            val mFragmentManager = parentFragmentManager
/*            mFragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, mLoginFragment, LoginFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }*/
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}