package com.epam.valkaryne.openmovieapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase

class ViewModelFactory(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val getQueryHistoryUseCase: GetQueryHistoryUseCase,
    private val saveQueryHistoryUseCase: SaveQueryHistoryUseCase,
    private val clearQueryHistoryUseCase: ClearQueryHistoryUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchMoviesViewModel::class.java)) {
            return SearchMoviesViewModel(
                searchMoviesUseCase,
                getQueryHistoryUseCase,
                saveQueryHistoryUseCase,
                clearQueryHistoryUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}