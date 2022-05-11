package com.munbonecci.cryptprika.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munbonecci.cryptprika.databinding.FragmentHomeBinding
import com.munbonecci.cryptprika.paprika_list.domain.model.Coin
import com.munbonecci.cryptprika.paprika_list.presentation.adapter.PaprikaListAdapter
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
        paprikaListAdapter = PaprikaListAdapter(clickListener = (object : PaprikaListAdapter.OnClickListener{
            override fun onItemClick(coin: Coin, position: Int) {
                Log.d("item pressed: $position ", coin.name)
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
                paprikaListAdapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}