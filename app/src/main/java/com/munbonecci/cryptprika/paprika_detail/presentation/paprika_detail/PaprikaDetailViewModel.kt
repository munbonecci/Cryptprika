package com.munbonecci.cryptprika.paprika_detail.presentation.paprika_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.paprika_detail.domain.model.CoinDetail
import com.munbonecci.cryptprika.paprika_detail.domain.use_case.GetPaprikaDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PaprikaDetailViewModel @Inject constructor(
    private val getPaprikaDetailUseCase: GetPaprikaDetailUseCase,
) : ViewModel() {

    data class ViewState(
        val isLoading: Boolean = false,
        val coin: CoinDetail? = null,
        val error: Error? = null,
    )

    val getCoinDetail = MutableLiveData(ViewState())

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
}