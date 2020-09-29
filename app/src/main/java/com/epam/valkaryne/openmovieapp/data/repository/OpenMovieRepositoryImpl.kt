package com.epam.valkaryne.openmovieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.OpenMovieDataSource
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository
import kotlinx.coroutines.flow.Flow

class OpenMovieRepositoryImpl(private val service: OpenMovieApiService) : OpenMovieRepository {

    override fun getSearchResult(query: QueryModel): Flow<PagingData<MovieInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { OpenMovieDataSource(service, query) }
        ).flow
    }

    private companion object {
        const val NETWORK_PAGE_SIZE = 10
        const val PREFETCH_DISTANCE = 5
    }
}