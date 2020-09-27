package com.epam.valkaryne.openmovieapp.domain;

import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;

import io.reactivex.Observable;

public interface OpenMovieRepository {

    Observable<PagingData<MovieDataModel>> getSearchResult(String query);
}
