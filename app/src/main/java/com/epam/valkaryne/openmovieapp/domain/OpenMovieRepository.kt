package com.epam.valkaryne.openmovieapp.domain

import androidx.paging.PagingData
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import kotlinx.coroutines.flow.Flow

/**
 * Repository that works with OpenMovie API
 */
interface OpenMovieRepository {

    /**
     * Search movies in API by query
     *
     * @param query a query to search
     * @return asynchronous data stream with search result
     */
    fun getSearchResult(query: QueryModel): Flow<PagingData<MovieInfo>>
}