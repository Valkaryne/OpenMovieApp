package com.epam.valkaryne.openmovieapp.data.api

import androidx.paging.PagingSource
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.data.api.model.OpenMovieSearchResponse
import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService

private const val OPEN_MOVIE_STARTING_PAGE_INDEX = 1

/**
 * Data source to send queries to API and to get response from
 */
class OpenMovieDataSource(private val service: OpenMovieApiService, private val query: QueryModel) :
    PagingSource<Int, MovieInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieInfo> {
        val position = params.key ?: OPEN_MOVIE_STARTING_PAGE_INDEX
        val searchQuery = "${query.title}*"
        return try {
            val response = service.searchMovies(searchQuery, query.type, query.year, position)
            if (response.successful) {
                toPage(response, position)
            } else {
                toError(response)
            }
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }

    private fun toError(response: OpenMovieSearchResponse): LoadResult<Int, MovieInfo> =
        LoadResult.Error(Exception(response.error))

    private fun toPage(response: OpenMovieSearchResponse, page: Int): LoadResult<Int, MovieInfo> =
        LoadResult.Page(
            data = response.searchResults ?: emptyList(),
            prevKey = if (page == OPEN_MOVIE_STARTING_PAGE_INDEX) null else page - 1,
            nextKey = if (response.searchResults?.isNullOrEmpty() != false) null else page + 1
        )
}