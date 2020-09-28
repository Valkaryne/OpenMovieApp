package com.epam.valkaryne.openmovieapp.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final SearchMoviesUseCase searchMoviesUseCase;
    private final GetQueryHistoryUseCase getQueryHistoryUseCase;
    private final SaveQueryHistoryUseCase saveQueryHistoryUseCase;
    private final ClearQueryHistoryUseCase clearQueryHistoryUseCase;

    public ViewModelFactory(SearchMoviesUseCase searchMoviesUseCase,
                            GetQueryHistoryUseCase getQueryHistoryUseCase,
                            SaveQueryHistoryUseCase saveQueryHistoryUseCase,
                            ClearQueryHistoryUseCase clearQueryHistoryUseCase) {
        this.searchMoviesUseCase = searchMoviesUseCase;
        this.getQueryHistoryUseCase = getQueryHistoryUseCase;
        this.saveQueryHistoryUseCase = saveQueryHistoryUseCase;
        this.clearQueryHistoryUseCase = clearQueryHistoryUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchMoviesViewModel.class)) {
            return (T) new SearchMoviesViewModel(
                    searchMoviesUseCase,
                    getQueryHistoryUseCase,
                    saveQueryHistoryUseCase,
                    clearQueryHistoryUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
