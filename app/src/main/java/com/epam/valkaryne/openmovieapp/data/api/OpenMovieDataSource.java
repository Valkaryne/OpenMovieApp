package com.epam.valkaryne.openmovieapp.data.api;

import androidx.paging.rxjava2.RxPagingSource;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;
import com.epam.valkaryne.openmovieapp.data.api.model.OpenMovieSearchResponse;
import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class OpenMovieDataSource extends RxPagingSource<Integer, MovieInfo> {

    private final int OPEN_MOVIE_PAGE_INDEX = 1;
    private final String EXTENDED_SEARCH_POSTFIX = "*";

    private OpenMovieApiService service;
    private QueryModel query;

    public OpenMovieDataSource(OpenMovieApiService service, QueryModel query) {
        this.service = service;
        this.query = query;
    }

    @NotNull
    @Override
    public Single<LoadResult<Integer, MovieInfo>> loadSingle(@NotNull LoadParams<Integer> loadParams) {
        Integer page = loadParams.getKey();
        if (page == null) {
            page = OPEN_MOVIE_PAGE_INDEX;
        }

        final Integer finalPage = page;
        return service.searchMovies(query.getTitle() + EXTENDED_SEARCH_POSTFIX, query.getType(), query.getYear(), page)
                .subscribeOn(Schedulers.io())
                .map(response -> {
                    if (response.isSuccessful()) {
                        return toPage(response, finalPage);
                    } else {
                        return toError(response);
                    }
                })
                .onErrorReturn(LoadResult.Error::new);
    }

    private LoadResult<Integer, MovieInfo> toError(OpenMovieSearchResponse response) {
        Exception error = new Exception(response.getError());
        return new LoadResult.Error<>(error);
    }

    private LoadResult<Integer, MovieInfo> toPage(OpenMovieSearchResponse response, int page) {
        return new LoadResult.Page<>(
                response.getSearchResults(),
                page == OPEN_MOVIE_PAGE_INDEX ? null : page - 1,
                page == (response.getTotalResults() / 10 + 1) ? null : page + 1
        );
    }
}
