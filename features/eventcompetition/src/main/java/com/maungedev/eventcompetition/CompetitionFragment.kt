package com.maungedev.eventcompetition

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.EventIT
import com.maungedev.eventcompetition.databinding.FragmentCompetitionBinding
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter

class CompetitionFragment : Fragment() {

    private lateinit var viewModel: CompetitionViewModel
    private var _binding: FragmentCompetitionBinding? = null
    private val binding get() = _binding!!

    private lateinit var competitionAdapter: MiniLayoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(CompetitionViewModel::class.java)

        _binding = FragmentCompetitionBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavoriteEvent().observe(viewLifecycleOwner,::setFavoriteEvent)

    }

    private fun setFavoriteEvent(list: List<EventIT>) {
        Log.d("FavoriteFragmentDebugList","$list"
        )
        competitionAdapter = MiniLayoutAdapter(requireContext())
        competitionAdapter.setItems(list)
        binding.rvCompetition.adapter = competitionAdapter
        binding.rvCompetition.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }
}