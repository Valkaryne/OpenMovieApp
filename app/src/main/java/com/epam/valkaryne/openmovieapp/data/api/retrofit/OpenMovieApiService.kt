package com.epam.valkaryne.openmovieapp.data.api.retrofit

import com.epam.valkaryne.openmovieapp.data.api.model.OpenMovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMovieApiService {

    @GET("?apikey=<your_key>")
    suspend fun searchMovies(
        @Query("s") search: String,
        @Query("type") type: String,
        @Query("y") year: String,
        @Query("page") page: Int
    ): OpenMovieSearchResponse
}