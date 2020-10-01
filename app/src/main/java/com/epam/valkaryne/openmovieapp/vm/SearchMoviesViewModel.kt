package com.epam.valkaryne.openmovieapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.Flow

/**
 * ViewModel that works with searching movies in OMDB API
 */
class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    var lastQuery: QueryModel? = null
        private set

    /**
     * Searches movies by query
     *
     * @param query a query
     * @return flow data with movies list
     */
    fun searchMovies(query: QueryModel): Flow<PagingData<MovieInfo>> {
        lastQuery = query
        return searchMoviesUseCase.executeUseCase(query).cachedIn(viewModelScope)
    }
}