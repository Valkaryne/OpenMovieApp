package com.epam.valkaryne.openmovieapp.data.api.retrofit;

import com.epam.valkaryne.openmovieapp.data.api.model.OpenMovieSearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenMovieApiService {

    @GET("?apikey=<your_key>")
    Single<OpenMovieSearchResponse> searchMovies(
            @Query("s") String search,
            @Query("type") String type,
            @Query("y") String year,
            @Query("page") int page
    );
}
