package com.munbonecci.cryptprika.paprika_detail.presentation.paprika_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.munbonecci.cryptprika.databinding.FragmentPaprikaDetailBinding
import com.munbonecci.cryptprika.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class PaprikaDetailFragment : Fragment() {

    private val paprikaDetailViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentPaprikaDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaprikaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}