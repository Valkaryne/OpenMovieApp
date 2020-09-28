package com.epam.valkaryne.openmovieapp.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingData;

import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;

import java.util.List;

import io.reactivex.Observable;

public class SearchMoviesViewModel extends ViewModel {

    private final SearchMoviesUseCase searchMoviesUseCase;

    private final GetQueryHistoryUseCase getQueryHistoryUseCase;
    private final SaveQueryHistoryUseCase saveQueryHistoryUseCase;
    private final ClearQueryHistoryUseCase clearQueryHistoryUseCase;

    private QueryModel lastQuery = null;

    private MutableLiveData<List<QueryModel>> _queryHistory = new MutableLiveData<>();

    public SearchMoviesViewModel(SearchMoviesUseCase searchMoviesUseCase,
                                 GetQueryHistoryUseCase getQueryHistoryUseCase,
                                 SaveQueryHistoryUseCase saveQueryHistoryUseCase,
                                 ClearQueryHistoryUseCase clearQueryHistoryUseCase) {
        this.searchMoviesUseCase = searchMoviesUseCase;
        this.getQueryHistoryUseCase = getQueryHistoryUseCase;
        this.saveQueryHistoryUseCase = saveQueryHistoryUseCase;
        this.clearQueryHistoryUseCase = clearQueryHistoryUseCase;
    }

    public Observable<PagingData<MovieInfo>> searchMovies(QueryModel query) {
        lastQuery = query;
        return searchMoviesUseCase.executeUseCase(query)
                .cache();
    }

    public LiveData<List<QueryModel>> getQueryHistory() {
        updateQueryHistory();
        return _queryHistory;
    }

    public void saveQueryHistory(QueryModel query) {
        saveQueryHistoryUseCase.executeUseCase(query);
    }

    public void clearQueryHistory() {
        clearQueryHistoryUseCase.executeUseCase();
        updateQueryHistory();
    }

    public QueryModel getLastQuery() {
        return lastQuery;
    }

    private void updateQueryHistory() {
        List<QueryModel> history = getQueryHistoryUseCase.executeUseCase();
        _queryHistory.setValue(history);
    }
}
