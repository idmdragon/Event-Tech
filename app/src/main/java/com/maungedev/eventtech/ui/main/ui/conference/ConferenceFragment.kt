package com.maungedev.eventtech.ui.main.ui.conference

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maungedev.eventtech.databinding.FragmentConferenceBinding

class ConferenceFragment : Fragment() {

    private lateinit var conferenceViewModel: ConferenceViewModel
    private var _binding: FragmentConferenceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        conferenceViewModel =
            ViewModelProvider(this).get(ConferenceViewModel::class.java)

        _binding = FragmentConferenceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        conferenceViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}