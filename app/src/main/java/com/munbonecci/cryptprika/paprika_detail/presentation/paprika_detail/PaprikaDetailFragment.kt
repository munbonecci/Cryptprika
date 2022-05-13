package com.munbonecci.cryptprika.paprika_detail.presentation.paprika_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.Constants.CHART_7_DAYS
import com.munbonecci.cryptprika.common.Constants.CHART_BASE_URL
import com.munbonecci.cryptprika.common.Constants.COIN_LOGO_BASE_URL
import com.munbonecci.cryptprika.common.Constants.LOGO_PNG
import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.databinding.FragmentPaprikaDetailBinding
import com.munbonecci.cryptprika.paprika_detail.domain.model.CoinDetail
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings


@AndroidEntryPoint
@WithFragmentBindings
class PaprikaDetailFragment : Fragment() {

    private val paprikaDetailViewModel: PaprikaDetailViewModel by activityViewModels()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCoinDetails()
    }

    private fun getCoinDetails() {
        var coinId = ""

        arguments?.let {
            coinId = it.getString("coin_id", "")
        }

        paprikaDetailViewModel.fetchCoinDetail(coinId)
        paprikaDetailViewModel.getCoinDetail.observe(viewLifecycleOwner) { state ->
            state.coin?.let { coin ->
                setCoinDetail(coin)
            }
            state.error?.let { error ->
                setError(error)
            }
            state.isLoading.let { isLoading ->
                if (isLoading) binding.loadingScreenAnimationView.visibility = View.VISIBLE
                else binding.loadingScreenAnimationView.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCoinDetail(coin: CoinDetail) {
        binding.coinNameText.text = "${coin.rank} - ${coin.name} (${coin.symbol})"
        binding.coinLogoImage.load("${COIN_LOGO_BASE_URL}${coin.coinId}${LOGO_PNG}")
        binding.coinChartImage.load("$CHART_BASE_URL${coin.coinId}$CHART_7_DAYS")
        binding.coinInfoText.text = coin.description
        setCoinStatus(coin)
    }

    private fun setCoinStatus(coin: CoinDetail) {
        val statusName =
            "${if (coin.isNew) "New ${coin.type}! -" else ""} ${if (coin.isActive) "Active" else "Inactive"}"
        val stateColor: Int = if (coin.isActive) R.color.green else R.color.red
        binding.coinStatusText.text = statusName
        binding.coinStatusText.setTextColor(ContextCompat.getColor(requireActivity(), stateColor))
    }

    private fun setError(error: Error) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}