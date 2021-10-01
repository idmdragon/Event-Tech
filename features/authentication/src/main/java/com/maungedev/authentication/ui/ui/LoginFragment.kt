package com.maungedev.authentication.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maungedev.authentication.R
import com.maungedev.authentication.databinding.FragmentLoginBinding
import com.maungedev.authentication.ui.di.authModule
import org.koin.core.context.loadKoinModules

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(authModule)

        binding.tvDaftar.setOnClickListener {
            val mRegisterFragment = RegisterFragment()
            val mFragmentManager = parentFragmentManager
            mFragmentManager.beginTransaction().apply {
                replace(
                    R.id.frame_container,
                    mRegisterFragment,
                    RegisterFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }
    }


}