package com.munbonecci.cryptprika.favorites.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.ItemOffsetDecoration
import com.munbonecci.cryptprika.database.favorites.Favorite
import com.munbonecci.cryptprika.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings


@AndroidEntryPoint
@WithFragmentBindings
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by activityViewModels()
    private lateinit var favoritesAdapter: FavoritesAdapter
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getFavoritesFromDB()
    }

    private fun initRecyclerView() {
        favoritesAdapter =
            FavoritesAdapter(clickListener = (object : FavoritesAdapter.OnClickListener {
                override fun onItemClick(favorite: Favorite, position: Int) {
                    Log.d("item pressed: $position ", favorite.name)
                    val bundle = Bundle().apply { putString("coin_id", favorite.id) }
                    findNavController().navigate(R.id.action_favorites_to_detail, bundle)
                }
            }))

        binding.favoritesRecycler.apply {
            adapter = favoritesAdapter
            layoutManager = GridLayoutManager(this.context, 3)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(ItemOffsetDecoration(3, 5, true))
        }
    }

    private fun getFavoritesFromDB() {
        viewModel.getFavoritesDb()
        viewModel.getFavoritesDbLiveData.observe(viewLifecycleOwner) { state ->
            state.isLoading.let { isLoading ->
                if (isLoading) binding.loadingAnimationView.visibility = View.VISIBLE
                else binding.loadingAnimationView.visibility = View.GONE
            }
            state.getFavorites?.let { favorites ->
                if (favorites.isNotEmpty()) {
                    hideNotFoundAnimation()
                    binding.favoritesRecycler.visibility = View.VISIBLE
                    favoritesAdapter.submitList(favorites.sortedBy { it.name })
                } else {
                    binding.favoritesRecycler.visibility = View.GONE
                    showNotFoundAnimation()
                }
            }
            state.error?.let { error ->
                Log.e("Error fetching favorites: ", error.message ?: "")
            }
        }
    }

    private fun showNotFoundAnimation() {
        binding.notFoundAnimationView.visibility = View.VISIBLE
        binding.noFavoritesText.visibility = View.VISIBLE
    }

    private fun hideNotFoundAnimation() {
        binding.notFoundAnimationView.visibility = View.GONE
        binding.noFavoritesText.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}