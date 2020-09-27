package com.epam.valkaryne.openmovieapp.data.api;

import androidx.paging.rxjava2.RxPagingSource;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;
import com.epam.valkaryne.openmovieapp.data.api.model.OpenMovieSearchResponse;
import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class OpenMovieDataSource extends RxPagingSource<Integer, MovieDataModel> {

    private final int OPEN_MOVIE_PAGE_INDEX = 1;

    private OpenMovieApiService service;
    private String query;

    public OpenMovieDataSource(OpenMovieApiService service, String query) {
        this.service = service;
        this.query = query;
    }

    @NotNull
    @Override
    public Single<LoadResult<Integer, MovieDataModel>> loadSingle(@NotNull LoadParams<Integer> loadParams) {
        Integer page = loadParams.getKey();
        if (page == null) {
            page = OPEN_MOVIE_PAGE_INDEX;
        }

        final Integer finalPage = page;
        return service.searchMovies("gam*", "", "2020", page)
                .subscribeOn(Schedulers.io())
                .map((Function<OpenMovieSearchResponse, LoadResult<Integer, MovieDataModel>>) response -> {
                    if (response.isSuccessful()) {
                        return toPage(response, finalPage);
                    } else {
                        return toError(response);
                    }
                })
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, MovieDataModel> toError(OpenMovieSearchResponse response) {
        Exception error = new Exception(response.getError());
        return new LoadResult.Error<>(error);
    }

    private LoadResult<Integer, MovieDataModel> toPage(OpenMovieSearchResponse response, int page) {
        return new LoadResult.Page<>(
                response.getSearchResults(),
                page == OPEN_MOVIE_PAGE_INDEX ? null : page - 1,
                page == (response.getTotalResults() / 10 + 1) ? null : page + 1
        );
    }
}
