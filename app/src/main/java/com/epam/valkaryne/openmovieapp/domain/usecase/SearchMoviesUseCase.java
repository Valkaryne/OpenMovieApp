package com.epam.valkaryne.openmovieapp.domain.usecase;

import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository;

import io.reactivex.Observable;

public class SearchMoviesUseCase {

    private OpenMovieRepository openMovieRepository;

    public SearchMoviesUseCase(OpenMovieRepository openMovieRepository) {
        this.openMovieRepository = openMovieRepository;
    }

    public Observable<PagingData<MovieInfo>> executeUseCase(QueryModel query) {
        return openMovieRepository.getSearchResult(query);
    }
}
