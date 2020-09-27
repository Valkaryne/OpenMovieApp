package com.epam.valkaryne.openmovieapp.domain.usecase;

import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository;

import io.reactivex.Observable;

public class SearchMoviesUseCase {

    private OpenMovieRepository openMovieRepository;

    public SearchMoviesUseCase(OpenMovieRepository openMovieRepository) {
        this.openMovieRepository = openMovieRepository;
    }

    public Observable<PagingData<MovieDataModel>> executeUseCase(String query) {
        return openMovieRepository.getSearchResult(query);
    }
}
