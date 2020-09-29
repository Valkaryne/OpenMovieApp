package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class SearchMoviesUseCaseTest {

    private val mockOpenMovieRepository: OpenMovieRepository = mockk()
    private val searchMoviesUseCase = SearchMoviesUseCase(mockOpenMovieRepository)

    @Test
    fun `when execute usecase should call getSearchResult method with argument`() {
        searchMoviesUseCase.executeUseCase(query)

        verify { mockOpenMovieRepository.getSearchResult(query) }
    }

    private companion object {
        val query = QueryModel("Tenet", "2020", "movie")
    }
}