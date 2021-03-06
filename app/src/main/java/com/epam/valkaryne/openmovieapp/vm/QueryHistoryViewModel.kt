package com.epam.valkaryne.openmovieapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel that works with storage of queries' history
 */
class QueryHistoryViewModel(
    private val getQueryHistoryUseCase: GetQueryHistoryUseCase,
    private val saveQueryHistoryUseCase: SaveQueryHistoryUseCase,
    private val clearQueryHistoryUseCase: ClearQueryHistoryUseCase
) : ViewModel() {

    private val _queryHistory: MutableLiveData<List<QueryModel>> = MutableLiveData()
    val queryHistory: LiveData<List<QueryModel>>
        get() {
            updateQueryHistory()
            return _queryHistory
        }

    /**
     * Saves the query in history
     *
     * @param query a query to save
     */
    fun saveQueryHistory(query: QueryModel) {
        viewModelScope.launch {
            saveQueryHistoryUseCase.executeUseCase(query)
        }
    }

    /**
     * Clears the history of queries
     */
    fun clearQueryHistory() {
        viewModelScope.launch {
            clearQueryHistoryUseCase.executeUseCase()
        }
        updateQueryHistory()
    }

    private fun updateQueryHistory() {
        viewModelScope.launch {
            val history = getQueryHistoryUseCase.executeUseCase()
            _queryHistory.value = history
        }
    }
}