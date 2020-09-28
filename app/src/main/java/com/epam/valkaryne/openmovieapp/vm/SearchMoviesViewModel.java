package com.epam.valkaryne.openmovieapp.vm;

import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;

import io.reactivex.Observable;

public class SearchMoviesViewModel extends ViewModel {

    private final SearchMoviesUseCase searchMoviesUseCase;

    public SearchMoviesViewModel(SearchMoviesUseCase searchMoviesUseCase) {
        this.searchMoviesUseCase = searchMoviesUseCase;
    }

    public Observable<PagingData<MovieInfo>> searchMovies(QueryModel query) {
        return searchMoviesUseCase.executeUseCase(query)
                .cache();
    }
}
