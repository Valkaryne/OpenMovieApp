package com.epam.valkaryne.openmovieapp.di

import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase
import com.epam.valkaryne.openmovieapp.vm.SearchMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListModule = module {
    factory { SearchMoviesUseCase(openMovieRepository = get()) }
    factory { GetQueryHistoryUseCase(queryRepository = get()) }
    factory { SaveQueryHistoryUseCase(queryRepository = get()) }
    factory { ClearQueryHistoryUseCase(queryRepository = get()) }

    viewModel {
        SearchMoviesViewModel(
            searchMoviesUseCase = get(),
            getQueryHistoryUseCase = get(),
            saveQueryHistoryUseCase = get(),
            clearQueryHistoryUseCase = get()
        )
    }
}