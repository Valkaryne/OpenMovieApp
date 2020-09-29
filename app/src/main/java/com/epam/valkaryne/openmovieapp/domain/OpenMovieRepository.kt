package com.epam.valkaryne.openmovieapp.domain

import androidx.paging.PagingData
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import kotlinx.coroutines.flow.Flow

interface OpenMovieRepository {

    fun getSearchResult(query: QueryModel): Flow<PagingData<MovieInfo>>
}