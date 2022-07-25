package com.munbonecci.cryptprika.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.features.paprika_list.domain.model.Coin
import com.munbonecci.cryptprika.features.paprika_list.domain.use_case.GetPaprikaListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPaprikaListUseCase: GetPaprikaListUseCase,
) : ViewModel() {

    data class ViewState(
        val isLoading: Boolean = false,
        val coins: List<Coin> = emptyList(),
        val error: Error? = null,
    )

    val getCoinList = MutableLiveData(ViewState())

    fun getCoins() {
        getCoinList.value = ViewState(isLoading = true)
        getPaprikaListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getCoinList.value =
                        ViewState(coins = result.value ?: emptyList(), isLoading = false)
                }
                is Resource.Error -> {
                    getCoinList.value = ViewState(
                        error = Error(
                            resourceId = R.string.unexpected_error_message,
                            message = result.message
                        ), isLoading = false
                    )
                }
                is Resource.NetworkError -> {
                    getCoinList.value =
                        ViewState(
                            error = Error(resourceId = R.string.connection_error),
                            isLoading = false
                        )
                }
            }
        }.launchIn(viewModelScope)
    }
}