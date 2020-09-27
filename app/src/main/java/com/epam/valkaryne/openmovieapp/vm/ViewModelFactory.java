package com.epam.valkaryne.openmovieapp.vm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final SearchMoviesUseCase useCase;

    public ViewModelFactory(SearchMoviesUseCase useCase) {
        this.useCase = useCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchMoviesViewModel.class)) {
            return (T) new SearchMoviesViewModel(useCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
