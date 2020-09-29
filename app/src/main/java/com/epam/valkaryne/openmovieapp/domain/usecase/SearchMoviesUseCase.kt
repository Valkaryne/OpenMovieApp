package com.epam.valkaryne.openmovieapp.domain.usecase

import androidx.paging.PagingData
import com.epam.valkaryne.openmovieapp.core.model.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val openMovieRepository: OpenMovieRepository) {

    fun executeUseCase(query: QueryModel): Flow<PagingData<MovieInfo>> =
        openMovieRepository.getSearchResult(query)
}