package com.epam.valkaryne.openmovieapp.domain;

import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;

import io.reactivex.Observable;

public interface OpenMovieRepository {

    Observable<PagingData<MovieInfo>> getSearchResult(QueryModel query);
}
