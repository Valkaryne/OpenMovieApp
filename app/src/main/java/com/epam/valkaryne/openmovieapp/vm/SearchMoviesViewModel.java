package com.epam.valkaryne.openmovieapp.vm;

import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;

import io.reactivex.Observable;

public class SearchMoviesViewModel extends ViewModel {

    private final SearchMoviesUseCase searchMoviesUseCase;

    public SearchMoviesViewModel(SearchMoviesUseCase searchMoviesUseCase) {
        this.searchMoviesUseCase = searchMoviesUseCase;
    }

    public Observable<PagingData<MovieDataModel>> searchMovies(String query) {
        return searchMoviesUseCase.executeUseCase(query)
                .cache();
    }
}
