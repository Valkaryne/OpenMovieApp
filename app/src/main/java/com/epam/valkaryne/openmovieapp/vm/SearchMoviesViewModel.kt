package com.epam.valkaryne.openmovieapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.Flow

class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getQueryHistoryUseCase: GetQueryHistoryUseCase,
    private val saveQueryHistoryUseCase: SaveQueryHistoryUseCase,
    private val clearQueryHistoryUseCase: ClearQueryHistoryUseCase
) : ViewModel() {

    var lastQuery: QueryModel? = null
        private set

    private val _queryHistory: MutableLiveData<List<QueryModel>> = MutableLiveData()
    val queryHistory: LiveData<List<QueryModel>>
        get() {
            updateQueryHistory()
            return _queryHistory
        }

    fun searchMovies(query: QueryModel): Flow<PagingData<MovieInfo>> {
        lastQuery = query
        return searchMoviesUseCase.executeUseCase(query).cachedIn(viewModelScope)
    }

    fun saveQueryHistory(query: QueryModel) {
        saveQueryHistoryUseCase.executeUseCase(query)
    }

    fun clearQueryHistory() {
        clearQueryHistoryUseCase.executeUseCase()
        updateQueryHistory()
    }

    private fun updateQueryHistory() {
        val history = getQueryHistoryUseCase.executeUseCase()
        _queryHistory.value = history
    }
}