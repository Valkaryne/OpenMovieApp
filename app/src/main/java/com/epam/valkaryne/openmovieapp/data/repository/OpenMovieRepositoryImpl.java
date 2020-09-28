package com.epam.valkaryne.openmovieapp.data.repository;

import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava2.PagingRx;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.data.api.OpenMovieDataSource;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;
import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService;
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository;

import io.reactivex.Observable;

public class OpenMovieRepositoryImpl implements OpenMovieRepository {

    private final int NETWORK_PAGE_SIZE = 10;
    private final int PREFETCH_DISTANCE = 5;

    private OpenMovieApiService service;

    public OpenMovieRepositoryImpl(OpenMovieApiService service) {
        this.service = service;
    }

    @Override
    public Observable<PagingData<MovieInfo>> getSearchResult(final QueryModel query) {
        return PagingRx.getObservable(
                new Pager(
                        new PagingConfig(NETWORK_PAGE_SIZE, PREFETCH_DISTANCE, false),
                        () -> new OpenMovieDataSource(service, query)
                )
        );
    }
}
