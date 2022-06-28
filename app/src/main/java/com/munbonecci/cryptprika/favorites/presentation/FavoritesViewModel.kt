package com.munbonecci.cryptprika.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.munbonecci.cryptprika.R
import com.munbonecci.cryptprika.common.Error
import com.munbonecci.cryptprika.common.Resource
import com.munbonecci.cryptprika.database.favorites.Favorites
import com.munbonecci.cryptprika.favorites.domain.model.DeleteFavoriteFromDBState
import com.munbonecci.cryptprika.favorites.domain.model.GetFavoriteFromDBState
import com.munbonecci.cryptprika.favorites.domain.model.GetFavoritesFromDBState
import com.munbonecci.cryptprika.favorites.domain.model.InsertFavoriteIntoDBState
import com.munbonecci.cryptprika.favorites.domain.use_case.DeleteFavoriteFromDBUseCase
import com.munbonecci.cryptprika.favorites.domain.use_case.GetFavoriteFromDBUseCase
import com.munbonecci.cryptprika.favorites.domain.use_case.GetFavoritesFromDBUseCase
import com.munbonecci.cryptprika.favorites.domain.use_case.InsertFavoriteIntoDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteFromDBUseCase: GetFavoriteFromDBUseCase,
    private val getFavoritesFromDBUseCase: GetFavoritesFromDBUseCase,
    private val deleteFavoriteFromDBUseCase: DeleteFavoriteFromDBUseCase,
    private val insertFavoriteIntoDBUseCase: InsertFavoriteIntoDBUseCase
) : ViewModel() {

    private val _getFavoriteDbLiveData: MutableLiveData<GetFavoriteFromDBState> = MutableLiveData()
    private val _getFavoritesDbLiveData: MutableLiveData<GetFavoritesFromDBState> =
        MutableLiveData()
    private val _deleteFavoriteDbLiveData: MutableLiveData<DeleteFavoriteFromDBState> =
        MutableLiveData()
    private val _insertFavoriteIntoDbLiveData: MutableLiveData<InsertFavoriteIntoDBState> =
        MutableLiveData()

    val getFavoriteDbLiveData: LiveData<GetFavoriteFromDBState>
        get() = _getFavoriteDbLiveData

    val getFavoritesDbLiveData: LiveData<GetFavoritesFromDBState>
        get() = _getFavoritesDbLiveData

    val deleteFavoriteDbLiveData: LiveData<DeleteFavoriteFromDBState>
        get() = _deleteFavoriteDbLiveData

    val insertFavoriteIntoDbLiveData: LiveData<InsertFavoriteIntoDBState>
        get() = _insertFavoriteIntoDbLiveData


    fun getFavoriteDb(id: String) {
        _getFavoriteDbLiveData.value = GetFavoriteFromDBState(isLoading = true)
        getFavoriteFromDBUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getFavoriteDbLiveData.postValue(
                        GetFavoriteFromDBState(
                            isLoading = false,
                            getFavorite = result.value?.getOrNull(id.toIntOrNull() ?: 0)
                                ?: Favorites()
                        )
                    )
                }
                is Resource.Error -> {
                    _getFavoriteDbLiveData.postValue(
                        GetFavoriteFromDBState(
                            isLoading = false,
                            error = Error(
                                resourceId = R.string.unexpected_error_message,
                                message = result.message
                            )
                        )
                    )
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getFavoritesDb() {
        _getFavoritesDbLiveData.value = GetFavoritesFromDBState(isLoading = true)
        getFavoritesFromDBUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _getFavoritesDbLiveData.postValue(
                        GetFavoritesFromDBState(
                            isLoading = false,
                            getFavorites = result.value ?: emptyList()
                        )
                    )
                }
                is Resource.Error -> {
                    _getFavoritesDbLiveData.postValue(
                        GetFavoritesFromDBState(
                            isLoading = false,
                            error = Error(
                                resourceId = R.string.unexpected_error_message,
                                message = result.message
                            )
                        )
                    )
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun deleteFavoriteFromDB(id: Int) {
        _deleteFavoriteDbLiveData.value = DeleteFavoriteFromDBState(isLoading = true)
        deleteFavoriteFromDBUseCase.invoke(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _deleteFavoriteDbLiveData.postValue(
                        DeleteFavoriteFromDBState(
                            isLoading = false,
                            isDeleted = true
                        )
                    )
                }
                is Resource.Error -> {
                    _deleteFavoriteDbLiveData.postValue(
                        DeleteFavoriteFromDBState(
                            isLoading = false,
                            isDeleted = false,
                            error = Error(
                                resourceId = R.string.unexpected_error_message,
                                message = result.message
                            )
                        )
                    )
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavoriteIntoDB(favorite: Favorites) {
        _insertFavoriteIntoDbLiveData.value = InsertFavoriteIntoDBState(isLoading = true)
        insertFavoriteIntoDBUseCase.invoke(favorite).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _insertFavoriteIntoDbLiveData.postValue(
                        InsertFavoriteIntoDBState(
                            isLoading = false,
                            isInserted = true
                        )
                    )
                }
                is Resource.Error -> {
                    _insertFavoriteIntoDbLiveData.postValue(
                        InsertFavoriteIntoDBState(
                            isLoading = false,
                            isInserted = false,
                            error = Error(
                                resourceId = R.string.unexpected_error_message,
                                message = result.message
                            )
                        )
                    )
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }


}