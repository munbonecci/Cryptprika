package com.munbonecci.cryptprika.paprika_detail.presentation.paprika_detail

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
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
import com.munbonecci.cryptprika.common.formatAsCurrency
import com.munbonecci.cryptprika.databinding.FragmentPaprikaDetailBinding
import com.munbonecci.cryptprika.favorites.presentation.FavoritesViewModel
import com.munbonecci.cryptprika.paprika_detail.domain.model.CoinDetail
import com.munbonecci.cryptprika.ticker_detail.domain.model.Ticker
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings


@AndroidEntryPoint
@WithFragmentBindings
class PaprikaDetailFragment : Fragment() {

    private val paprikaDetailViewModel: PaprikaDetailViewModel by activityViewModels()
    private val favoritesViewModel: FavoritesViewModel by activityViewModels()
    private var _binding: FragmentPaprikaDetailBinding? = null
    private val binding get() = _binding!!

    var coinId = ""

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
        arguments?.let {
            coinId = it.getString("coin_id", "")
        }
        getCoinDetails()
        getTickerDetails()
    }

    private fun getCoinDetails() {
        paprikaDetailViewModel.fetchCoinDetail(coinId)
        paprikaDetailViewModel.getCoinDetail.observe(viewLifecycleOwner) { state ->
            state.coin?.let { coin ->
                setCoinDetail(coin)
                getFavoriteFromDataBase(coinId)
            }
            state.error?.let { error ->
                setCoinError(error)
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

    private fun setCoinError(error: Error) {

    }

    private fun getTickerDetails() {
        paprikaDetailViewModel.fetchTickerDetail(coinId)
        paprikaDetailViewModel.getTickerDetail.observe(viewLifecycleOwner) { state ->
            state.ticker?.let { ticker ->
                setTickerDetail(ticker)
            }
            state.error?.let { error ->
                setTickerError(error)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTickerDetail(ticker: Ticker) {
        binding.includedTickerDetailLayout.root.visibility = View.VISIBLE
        with(binding.includedTickerDetailLayout) {
            tickerUsdPriceText.text = ticker.priceUsd.formatAsCurrency()
            tickerBTCPriceText.text = "BTC: ${ticker.priceBtc}"
            tickerImageView.load("${COIN_LOGO_BASE_URL}${ticker.id}${LOGO_PNG}")
            percentChange1hText.text = String.format(
                requireActivity().getString(
                    R.string.percentage_change_1h,
                    ticker.percentChange1h
                )
            )
            percentChange24hText.text = String.format(
                requireActivity().getString(
                    R.string.percentage_change_24h,
                    ticker.percentChange24h
                )
            )
            percentChange7DText.text = String.format(
                requireActivity().getString(
                    R.string.percentage_change_7d,
                    ticker.percentChange7d
                )
            )
        }
    }

    private fun setTickerError(error: Error) {
        Log.d("ticker_error: ", error.message ?: "")
        binding.includedTickerDetailLayout.root.visibility = View.GONE
    }

    private fun getFavoriteFromDataBase(coinId: String) {
        favoritesViewModel.getFavoriteDb(coinId)
        favoritesViewModel.getFavoriteDbLiveData.observe(viewLifecycleOwner) { state ->
            state.getFavorite?.let { favorite ->
                setFavoriteTextAndIcon(favorite.isFavoriteAdded)
            }
            state.error?.let { error ->
                setFavoriteTextAndIcon(false)
                Log.e("Error: ", error.message ?: "")
            }
        }
    }

    private fun setFavoriteTextAndIcon(favoriteAdded: Boolean) {
        val colorInt = ContextCompat.getColor(requireActivity(), R.color.purple_500)
        val colorStateList = ColorStateList.valueOf(colorInt)
        if (favoriteAdded) {
            binding.favoriteButton.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.ic_favorite_black_24,
                0,
                0
            )
            binding.favoriteButton.text = requireActivity().getString(R.string.remove_favorite_text)
        } else {
            binding.favoriteButton.setCompoundDrawablesWithIntrinsicBounds(
                0,
                R.drawable.ic_favorite_border_black_24,
                0,
                0
            )
            binding.favoriteButton.text = requireActivity().getString(R.string.add_favorite_text)
        }
        binding.favoriteButton.iconTint = colorStateList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}