package com.munbonecci.cryptprika.features.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.databinding.FragmentHomeBinding
import com.munbonecci.cryptprika.features.paprika_list.domain.model.Coin
import com.munbonecci.cryptprika.features.paprika_list.presentation.adapter.PaprikaListAdapter
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var paprikaListAdapter: PaprikaListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observePaprikaChanges()
    }

    private fun initRecyclerView() {
        paprikaListAdapter =
            PaprikaListAdapter(clickListener = (object : PaprikaListAdapter.OnClickListener {
                override fun onItemClick(coin: Coin, position: Int) {
                    Log.d("item pressed: $position ", coin.name)
                    val bundle = Bundle().apply { putString("coin_id", coin.id) }
                    findNavController().navigate(R.id.action_home_to_detail, bundle)
                }
            }))

        binding.paprikaRecycler.apply {
            adapter = paprikaListAdapter
            layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun observePaprikaChanges() {
        homeViewModel.getCoins()
        homeViewModel.getCoinList.observe(viewLifecycleOwner) { state ->
            state.coins.let {
                binding.loadingAnimationView.visibility = View.GONE
                binding.paprikaRecycler.visibility = View.VISIBLE
                paprikaListAdapter.submitList(it)
            }
            state.isLoading.let { isLoading ->
                if (isLoading) binding.loadingAnimationView.visibility = View.VISIBLE
                else binding.loadingAnimationView.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}