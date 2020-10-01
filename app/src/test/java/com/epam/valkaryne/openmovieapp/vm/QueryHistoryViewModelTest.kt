package com.epam.valkaryne.openmovieapp.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class QueryHistoryViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockGetQueryHistoryUseCase: GetQueryHistoryUseCase = mockk()
    private val mockSaveQueryHistoryUseCase: SaveQueryHistoryUseCase = mockk(relaxed = true)
    private val mockClearQueryHistoryUseCase: ClearQueryHistoryUseCase = mockk(relaxed = true)

    private val viewModel = QueryHistoryViewModel(
        mockGetQueryHistoryUseCase,
        mockSaveQueryHistoryUseCase,
        mockClearQueryHistoryUseCase
    )

    @Before
    fun `set up`() {
        every { mockGetQueryHistoryUseCase.executeUseCase() } returns listOf(query)
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
        val expectedQueryHistory = listOf(query)
        val actualQueryHistory = viewModel.queryHistory.value

        assertEquals(expectedQueryHistory, actualQueryHistory)
    }

    private companion object {
        val query = QueryModel("Tenet", "2020", "movie")
    }
}