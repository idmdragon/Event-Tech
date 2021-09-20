package com.maungedev.eventtech.ui.main.ui.competition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maungedev.eventtech.databinding.FragmentCompetitionBinding

class CompetitionFragment : Fragment() {

    private lateinit var competitionViewModel: CompetitionViewModel
    private var _binding: FragmentCompetitionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        competitionViewModel =
            ViewModelProvider(this).get(CompetitionViewModel::class.java)

        _binding = FragmentCompetitionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        competitionViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}