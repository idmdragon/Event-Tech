package com.maungedev.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maungedev.domain.model.Event
import com.maungedev.domain.utils.Resource
import com.maungedev.eventtech.ui.adapter.MiniLayoutAdapter
import com.maungedev.favorite.databinding.FragmentFavoriteBinding
import com.maungedev.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var favoriteEventAdapter: MiniLayoutAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(favoriteModule)
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrentUser().observe(viewLifecycleOwner,{
            it.data?.favorite.let { favoriteIds->
                if (favoriteIds != null) {
                    if(favoriteIds.isNotEmpty()){
                        isEmpty(false)
                        viewModel.getAllFavorite(favoriteIds).observe(viewLifecycleOwner,::setFavoriteEvent)
                    }else{
                        isEmpty(true)
                    }
                }
            }
        })
    }

    private fun setFavoriteEvent(resource: Resource<List<Event>>?) {
        when (resource) {
            is Resource.Success -> {
                loadingState(false)
                resource.data?.let { listItem ->
                    favoriteEventAdapter = MiniLayoutAdapter(requireContext())
                    favoriteEventAdapter.setItems(listItem.sortedBy {event -> event.numbersOfView })
                    favoriteEventAdapter.itemCount = listItem.size
                    binding.rvFavorite.adapter = favoriteEventAdapter
                    binding.rvFavorite.layoutManager = LinearLayoutManager(
                        activity,
                        LinearLayoutManager.VERTICAL, false
                    )
                }
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
        binding.progressBar.isVisible = state
    }

    private fun isEmpty(state: Boolean){
        binding.apply {
            layoutEmpty.isVisible = state
            rvFavorite.isVisible = !state
        }
    }


}