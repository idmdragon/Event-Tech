package com.maungedev.favorite.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maungedev.domain.model.EventIT
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter
import com.maungedev.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var favoriteEventAdapter: MiniLayoutAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteEvent().observe(viewLifecycleOwner,::setFavoriteEvent)

    }

    private fun setFavoriteEvent(list: List<EventIT>) {
        Log.d("FavoriteFragmentDebugList","$list"
        )
        favoriteEventAdapter = MiniLayoutAdapter(requireContext())
        favoriteEventAdapter.setItems(list)
        binding.rvFavorite.adapter = favoriteEventAdapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL, false
        )
    }
}