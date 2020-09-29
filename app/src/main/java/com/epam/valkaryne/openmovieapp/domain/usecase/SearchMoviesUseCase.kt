package com.epam.valkaryne.openmovieapp.domain.usecase

import androidx.paging.PagingData
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Domain logic to search movies in API database by query
 */
class SearchMoviesUseCase(private val openMovieRepository: OpenMovieRepository) {

    /**
     * Executes domain logic
     *
     * @param query a query to search movies
     * @return asynchronous data stream with found movies
     */
    fun executeUseCase(query: QueryModel): Flow<PagingData<MovieInfo>> =
        openMovieRepository.getSearchResult(query)
}