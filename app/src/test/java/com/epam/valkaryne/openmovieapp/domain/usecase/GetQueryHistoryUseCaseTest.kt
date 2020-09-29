package com.epam.valkaryne.openmovieapp.domain.usecase

import com.epam.valkaryne.openmovieapp.common.QueryModel
import com.epam.valkaryne.openmovieapp.domain.QueryRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class GetQueryHistoryUseCaseTest {

    private val mockQueryRepository: QueryRepository = mockk(relaxed = true)
    private val getQueryHistoryUseCase = GetQueryHistoryUseCase(mockQueryRepository)

    @Test
    fun `when execute usecase should call getQueries method`() {
        getQueryHistoryUseCase.executeUseCase()

        verify { mockQueryRepository.getQueries() }
    }

    @Test
    fun `when execute usecase should return list of queryModels`() {
        every { mockQueryRepository.getQueries() } returns listOf(queryModel)

        val actualQueryHistory = getQueryHistoryUseCase.executeUseCase()
        val expectedQueryHistory = listOf(queryModel)

        assertEquals(expectedQueryHistory, actualQueryHistory)
    }

    @Test
    fun `when execute usecase should not call clearRepository method`() {
        getQueryHistoryUseCase.executeUseCase()

        verify(exactly = 0) { mockQueryRepository.clearRepository() }
    }

    @Test
    fun `when execute usecase should not call saveQuery method`() {
        getQueryHistoryUseCase.executeUseCase()

        verify(exactly = 0) { mockQueryRepository.saveQuery(any()) }
    }

    private companion object {
        val queryModel = QueryModel("Tenet", "2020", "movie")
    }
}