package com.munbonecci.cryptprika.paprika_detail.presentation.paprika_detail

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
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    data class ViewState(
        val isLoading: Boolean = false,
        val coin: CoinDetail? = null,
        val error: Error? = null,
    )

    private val _state = mutableStateOf(ViewState())
    val getCoinDetail: State<ViewState> = _state

    init {
        savedStateHandle.get<String>("coinId")?.let {
            getCoinDetail(it)
        }
    }

    private fun getCoinDetail(coinId: String) {
        _state.value = ViewState(isLoading = true)
        getPaprikaDetailUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ViewState(coin = result.value)
                }
                is Resource.Error -> {
                    _state.value = ViewState(
                        error = Error(
                            resourceId = R.string.unexpected_error_message,
                            message = result.message
                        )
                    )
                }
                is Resource.NetworkError -> {
                    _state.value = ViewState(error = Error(resourceId = R.string.connection_error))
                }
            }
        }.launchIn(viewModelScope)
    }
}