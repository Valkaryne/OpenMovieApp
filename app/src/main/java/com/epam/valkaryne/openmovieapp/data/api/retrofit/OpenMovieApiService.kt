package com.epam.valkaryne.openmovieapp.data.api.retrofit

import com.epam.valkaryne.openmovieapp.BuildConfig
import com.epam.valkaryne.openmovieapp.data.api.model.OpenMovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit service that works with API
 *
 * ! Please, make sure that you put your api-key instead of <your_key> in build configs !
 */
interface OpenMovieApiService {

    @GET("?apikey=${BuildConfig.OMDB_API_KEY}")
    suspend fun searchMovies(
        @Query("s") search: String,
        @Query("type") type: String,
        @Query("y") year: String,
        @Query("page") page: Int
    ): OpenMovieSearchResponse
}