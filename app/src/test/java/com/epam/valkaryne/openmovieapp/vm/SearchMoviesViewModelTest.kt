package com.epam.valkaryne.openmovieapp.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class SearchMoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockSearchMoviesUseCase: SearchMoviesUseCase = mockk(relaxed = true)

    private val viewModel = SearchMoviesViewModel(mockSearchMoviesUseCase)

    @Test
    fun `when invoked searchMovies should interact with SearchMoviesUseCase`() {
        viewModel.searchMovies(query)

        verify { mockSearchMoviesUseCase.executeUseCase(query) }
    }

    @Test
    fun `when invoked searchMovies then last query value is not null`() {
        viewModel.searchMovies(query)

        assertNotNull(viewModel.lastQuery)
    }

    @Test
    fun `when invoked searchMovies should update last query value`() {
        viewModel.searchMovies(query)

        assertEquals(query, viewModel.lastQuery)
    }

    private companion object {
        val query = QueryModel("Tenet", "2020", "movie")
    }
}