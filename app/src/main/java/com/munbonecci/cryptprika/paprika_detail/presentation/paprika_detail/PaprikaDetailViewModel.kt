package com.munbonecci.cryptprika.paprika_detail.presentation.paprika_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.paprika_detail.domain.model.CoinDetail
import com.munbonecci.cryptprika.paprika_detail.domain.use_case.GetPaprikaDetailUseCase
import com.munbonecci.cryptprika.ticker_detail.domain.model.Ticker
import com.munbonecci.cryptprika.ticker_detail.domain.use_case.GetTickerDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PaprikaDetailViewModel @Inject constructor(
    private val getPaprikaDetailUseCase: GetPaprikaDetailUseCase,
    private val getTickerDetailUseCase: GetTickerDetailUseCase
) : ViewModel() {

    val getCoinDetail = MutableLiveData(ViewState())
    val getTickerDetail = MutableLiveData(ViewTickerState())

    fun fetchCoinDetail(coinId: String) {
        getCoinDetail.value = ViewState(isLoading = true)
        getPaprikaDetailUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getCoinDetail.value = ViewState(coin = result.value, isLoading = false)
                }
                is Resource.Error -> {
                    getCoinDetail.value = ViewState(
                        error = Error(
                            resourceId = R.string.unexpected_error_message,
                            message = result.message
                        ), isLoading = false
                    )
                }
                is Resource.NetworkError -> {
                    getCoinDetail.value =
                        ViewState(
                            error = Error(resourceId = R.string.connection_error),
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchTickerDetail(tickerId: String) {
        getTickerDetail.value = ViewTickerState(isLoading = true)
        getTickerDetailUseCase(tickerId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getTickerDetail.value =
                        ViewTickerState(ticker = result.value, isLoading = false)
                }
                is Resource.Error -> {
                    getTickerDetail.value = ViewTickerState(
                        error = Error(
                            resourceId = R.string.unexpected_error_message,
                            message = result.message
                        ), isLoading = false
                    )
                }
                is Resource.NetworkError -> {
                    getTickerDetail.value =
                        ViewTickerState(
                            error = Error(resourceId = R.string.connection_error),
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val coin: CoinDetail? = null,
        val error: Error? = null,
    )

    data class ViewTickerState(
        val isLoading: Boolean = false,
        val ticker: Ticker? = null,
        val error: Error? = null,
    )
}