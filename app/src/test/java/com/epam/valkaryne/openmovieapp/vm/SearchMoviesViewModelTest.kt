package com.epam.valkaryne.openmovieapp.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule

class SearchMoviesViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockSearchMoviesUseCase: SearchMoviesUseCase = mockk(relaxed = true)
    private val mockGetQueryHistoryUseCase: GetQueryHistoryUseCase = mockk(relaxed = true)
    private val mockSaveQueryHistoryUseCase: SaveQueryHistoryUseCase = mockk(relaxed = true)
    private val mockClearQueryHistoryUseCase: ClearQueryHistoryUseCase = mockk(relaxed = true)

    private val viewModel = SearchMoviesViewModel(
        mockSearchMoviesUseCase,
        mockGetQueryHistoryUseCase,
        mockSaveQueryHistoryUseCase,
        mockClearQueryHistoryUseCase
    )

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

    @Test
    fun `when invoked saveQueryHistory should interact with SaveQueryHistoryUseCase`() {
        viewModel.saveQueryHistory(query)

        verify { mockSaveQueryHistoryUseCase.executeUseCase(query) }
    }

    @Test
    fun `when invoked clearQueryHistory should interact with ClearHistoryUseCase`() {
        viewModel.clearQueryHistory()

        verify { mockClearQueryHistoryUseCase.executeUseCase() }
    }

    @Test
    fun `when invoked clearQueryHistory should interact with GetQueryHistoryUseCase`() {
        viewModel.clearQueryHistory()

        verify { mockGetQueryHistoryUseCase.executeUseCase() }
    }

    @Test
    fun `when get queryHistory should interact with GetQueryHistoryUseCase`() {
        viewModel.queryHistory

        verify { mockGetQueryHistoryUseCase.executeUseCase() }
    }

    @Test
    fun `when get queryHistory should return list of queryModels`() {
        every { mockGetQueryHistoryUseCase.executeUseCase() } returns listOf(query)

        val expectedQueryHistory = listOf(query)
        val actualQueryHistory = viewModel.queryHistory.value

        assertEquals(expectedQueryHistory, actualQueryHistory)
    }

    private companion object {
        val query = QueryModel("Tenet", "2020", "movie")
    }
}